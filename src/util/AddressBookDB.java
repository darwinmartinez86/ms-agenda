package util;

import entity.Company;
import entity.Contact;
import entity.Person;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AddressBookDB {

    List<Person> personList = new ArrayList<>();
    List<Company> companyList = new ArrayList<>();

    private Map<String, List<Person>> personNameIndex = new HashMap<>();
    private Map<String, List<Company>> companyNameIndex = new HashMap<>();
    private Map<String, List<Company>> companyCityIndex = new HashMap<>();

    private Map<String, List<Contact>> contactByPersonNameIndex = new HashMap<>();
    private Map<String, List<Contact>> contactByCompanyNameIndex = new HashMap<>();
    private Map<String, List<Contact>> contactByCompanyCityIndex = new HashMap<>();


    public void addPerson(Person person) {
        personList.add(person);
        personNameIndex.computeIfAbsent(person.getName(), k -> new ArrayList<>()).add(person);
    }

    public void addCompany(Company company) {
        companyList.add(company);
        companyNameIndex.computeIfAbsent(company.getName(), k -> new ArrayList<>()).add(company);
        companyCityIndex.computeIfAbsent(company.getCity(), k -> new ArrayList<>()).add(company);
    }

    public void addContact(Contact contact, boolean companyExists, boolean personExists) {

        if(!companyExists) {
            Long indexCompany = Long.valueOf(companyList.size() + 1);
            contact.getCompany().setId(indexCompany);
            addCompany(contact.getCompany());
        }

        if(!personExists) {
            Long indexPerson = Long.valueOf(personList.size() + 1);
            contact.getPerson().setId(indexPerson);
            addPerson(contact.getPerson());
        }

        contactByPersonNameIndex.computeIfAbsent(contact.getPerson().getName(), k -> new ArrayList<>()).add(contact);
        contactByCompanyNameIndex.computeIfAbsent(contact.getCompany().getName(), k -> new ArrayList<>()).add(contact);
        contactByCompanyCityIndex.computeIfAbsent(contact.getCompany().getCity(), k -> new ArrayList<>()).add(contact);
    }

    public List<Person> searchPersonByName(String namePattern) {
        Pattern pattern = Pattern.compile(namePattern.replace("*", ".*"), Pattern.CASE_INSENSITIVE);
        return personNameIndex.keySet()
                .stream()
                .filter(pattern.asPredicate())
                .flatMap(key -> personNameIndex.get(key).stream())
                .collect(Collectors.toList());
    }

    public List<Company> searchCompanyByName(String namePattern) {
        Pattern pattern = Pattern.compile(namePattern.replace("*", ".*"), Pattern.CASE_INSENSITIVE);
        return companyNameIndex.keySet()
                .stream()
                .filter(pattern.asPredicate())
                .flatMap(key -> companyNameIndex.get(key).stream())
                .collect(Collectors.toList());
    }

    public List<Company> searchCompanyByCity(String city) {
        return companyCityIndex.getOrDefault(city, new ArrayList<>());
    }

    public List<Company> searchCompanyByNameInCities(String namePattern, List<String> cities) {
        Pattern pattern = Pattern.compile(namePattern.replace("*", ".*"), Pattern.CASE_INSENSITIVE);
        return cities.stream()
                .flatMap(city -> companyCityIndex.getOrDefault(city, new ArrayList<>()).stream())
                .filter(empresa -> pattern.matcher(empresa.getName()).matches())
                .collect(Collectors.toList());
    }

    public List<Contact> searchContactByCity(String city) {
        return contactByCompanyCityIndex.getOrDefault(city, new ArrayList<>());
    }

    public Person searchPersonById(Long personId) {
        int index = (int) (personId - 1);
        if(personList.isEmpty() || index < 0 || personList.size() > personId)
            return null;
        return personList.get(index);
    }

    public Company searchCompanyById(Long companyId) {
        int index = (int) (companyId - 1);
        if(companyList.isEmpty() || index < 0 || companyList.size() > companyId)
            return null;
        return companyList.get(index);
    }

    public boolean personExists(Person person) {
        return personList.contains(person);
    }

    public boolean companyExists(Company company) {
        return companyList.contains(company);
    }


    public List<Contact> searchContactByPersonName(String namePattern) {
        Pattern pattern = Pattern.compile(namePattern.replace("*", ".*"), Pattern.CASE_INSENSITIVE);
        return contactByPersonNameIndex.keySet()
                .stream()
                .filter(pattern.asPredicate())
                .flatMap(key -> contactByPersonNameIndex.get(key).stream())
                .collect(Collectors.toList());
    }

    public List<Contact> searchContactByCompanyName(String namePattern) {
        Pattern pattern = Pattern.compile(namePattern.replace("*", ".*"), Pattern.CASE_INSENSITIVE);
        return contactByCompanyNameIndex.keySet()
                .stream()
                .filter(pattern.asPredicate())
                .flatMap(key -> contactByCompanyNameIndex.get(key).stream())
                .collect(Collectors.toList());
    }

    public List<Contact> searchContactByCompanyNameInCities(String namePattern, List<String> cities) {
        Pattern pattern = Pattern.compile(namePattern.replace("*", ".*"), Pattern.CASE_INSENSITIVE);
        return cities.stream()
                .flatMap(city -> contactByCompanyCityIndex.getOrDefault(city, new ArrayList<>()).stream())
                .filter(contact -> pattern.matcher(contact.getCompany().getName()).matches())
                .collect(Collectors.toList());
    }
}

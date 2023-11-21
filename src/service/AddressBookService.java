package service;

import entity.Company;
import entity.Contact;
import entity.Person;
import repository.CompanyRepository;
import repository.ContactRepository;
import repository.PersonRepository;

import java.util.List;

public class AddressBookService {

    private ContactRepository contactRepository;
    private PersonRepository personRepository;
    private CompanyRepository companyRepository;

    public AddressBookService(PersonRepository personRepository,CompanyRepository companyRepository,ContactRepository contactRepository) {
        this.personRepository = personRepository;
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
    }

    public void addPersonFromConsole(Person person) {

        if(personRepository.exists(person)) {
            System.out.println("La Persona que esta intentando agregar ya existe");
        } else {
            String personString = personRepository.addPerson(person);
            System.out.println("Persona agregada con éxito.");
            System.out.println(personString);
        }

    }

    public void addCompanyFromConsole(Company company) {

        if(companyRepository.exists(company)) {
            System.out.println("La Empresa que esta intentando agregar ya existe");
        } else {
            String companyString = companyRepository.addCompany(company);
            System.out.println("Compania agregada con éxito.");
            System.out.println(companyString);
        }

    }

    public void addContactFromConsole(Long companyId, Long personId, String rol) {

        Company company = companyRepository.getById(companyId);
        if(company == null) {
            throw new IllegalArgumentException("Empresa no encontrada, verifique el dato e intente nuevamente");
        }

        Person person = personRepository.getById(personId);
        if(person == null) {
            throw new IllegalArgumentException("Persona no encontrada, verifique el dato e intente nuevamente");
        }

        Contact contact = new Contact(person,company,rol);
        String contactString = contactRepository.addContact(contact, true, true);
        System.out.println("Contacto agregado con éxito.");
        System.out.println(contactString);
    }

    public void addContactFromConsole(Company company, Long personId, String rol) {

        Person person = personRepository.getById(personId);
        if(person == null) {
            throw new IllegalArgumentException("Persona no encontrada, verifique el dato e intente nuevamente");
        }

        if(companyRepository.exists(company)) {
            System.out.println("La Empresa que esta intentando agregar ya existe");
        } else {
            Contact contact = new Contact(person,company,rol);
            String contactString = contactRepository.addContact(contact, false, true);
            System.out.println("Contacto agregado con éxito.");
            System.out.println(contactString);
        }
    }

    public void addContactFromConsole(Long companyId, Person person, String rol) {

        Company company = companyRepository.getById(companyId);
        if(company == null) {
            throw new IllegalArgumentException("Empresa no encontrada, verifique el dato e intente nuevamente");
        }

        if(personRepository.exists(person)) {
            System.out.println("La Persona que esta intentando agregar ya existe");
        } else {
            Contact contact = new Contact(person, company, rol);
            String contactString = contactRepository.addContact(contact, true, false);
            System.out.println("Contacto agregado con éxito.");
            System.out.println(contactString);
        }
    }

    public void addContactFromConsole(Company company, Person person, String rol) {

        if(companyRepository.exists(company)) {
            System.out.println("La Empresa que esta intentando agregar ya existe");
        } else if(personRepository.exists(person)) {
            System.out.println("La Persona que esta intentando agregar ya existe");
        } else {
            Contact contact = new Contact(person, company, rol);
            String contactString = contactRepository.addContact(contact, false, false);
            System.out.println("Contacto agregado con éxito.");
            System.out.println(contactString);
        }
    }

    public void searchPersonByNameFromConsole(String name) {
        List<Person> result = personRepository.searchByName(name);
        printSearchPersonResult(result);
    }

    public void searchCompanyByNameFromConsole(String name) {
        List<Company> result = companyRepository.searchByName(name);
        printSearchCompanyResult(result);
    }

    public void searchCompanyByCityFromConsole(String city) {
        List<Company> result = companyRepository.searchByCity(city);
        printSearchCompanyResult(result);
    }

    public void searchByNameInCitiesFromConsole(String name, List<String> cities) {

        List<Company> result = companyRepository.searchByNameInCities(name, cities);
        printSearchCompanyResult(result);
    }

    public void searchContactByPersonName(String name) {
        List<Contact> result = contactRepository.searchByPersonName(name);
        printSearchContactResult(result);
    }

    public void searchContactByCompanyName(String name) {
        List<Contact> result = contactRepository.searchByCompanyName(name);
        printSearchContactResult(result);
    }

    public void searchContactByCity(String city) {
        List<Contact> result = contactRepository.searchByCompanyCity(city);
        printSearchContactResult(result);
    }

    public void searchContactByCompanyNameInCitiesFromConsole(String name, List<String> cities) {

        List<Contact> result = contactRepository.searchByCompanyNameInCities(name, cities);
        printSearchContactResult(result);
    }

    private void printSearchPersonResult(List<Person> result) {
        if (result.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            System.out.println("Resultados de la búsqueda:");
            for (Person person : result) {
                System.out.println(person.toString());
            }
        }
    }

    private void printSearchCompanyResult(List<Company> result) {
        if (result.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            System.out.println("Resultados de la búsqueda:");
            for (Company company : result) {
                System.out.println(company.toString());
            }
        }
    }

    private void printSearchContactResult(List<Contact> result) {
        if (result.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            System.out.println("Resultados de la búsqueda:");
            for (Contact contact : result) {
                System.out.println(contact.toString());
            }
        }
    }

}

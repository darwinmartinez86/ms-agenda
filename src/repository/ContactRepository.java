package repository;

import entity.Company;
import entity.Contact;
import util.AddressBookDB;

import java.util.List;

public class ContactRepository {

    private AddressBookDB addressBookDB;
    private Long id;

    public ContactRepository(AddressBookDB addressBookDB, Long id) {
        this.addressBookDB = addressBookDB;
        this.id = id;
    }

    public String addContact(Contact contact, boolean companyExists, boolean personExists) {
        contact.setId(++id);
        addressBookDB.addContact(contact, companyExists, personExists);
        return contact.toString();
    }

    public List<Contact> searchByPersonName(String name) {
        return addressBookDB.searchContactByPersonName(name);
    }

    public List<Contact> searchByCompanyName(String name) {
        return addressBookDB.searchContactByCompanyName(name);
    }

    public List<Contact> searchByCompanyCity(String name) {
        return addressBookDB.searchContactByCity(name);
    }

    public List<Contact> searchByCompanyNameInCities(String name, List<String> cities) {
        return addressBookDB.searchContactByCompanyNameInCities(name,cities);
    }
}

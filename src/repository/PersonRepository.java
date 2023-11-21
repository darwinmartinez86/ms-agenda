package repository;

import entity.Person;
import util.AddressBookDB;

import java.util.List;

public class PersonRepository {

    private AddressBookDB addressBookDB;
    private Long id;

    public PersonRepository(AddressBookDB addressBookDB, Long id) {
        this.addressBookDB = addressBookDB;
        this.id = id;
    }

    public String addPerson(Person person) {
        person.setId(++id);
        addressBookDB.addPerson(person);
        return person.toString();
    }

    public List<Person> searchByName(String name) {
        return addressBookDB.searchPersonByName(name);
    }

    public Person getById(Long personId) {
        return addressBookDB.searchPersonById(personId);
    }

    public boolean exists(Person person) { return addressBookDB.personExists(person); }
}

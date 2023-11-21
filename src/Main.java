import controller.AddressBookController;
import repository.CompanyRepository;
import repository.ContactRepository;
import repository.PersonRepository;
import service.AddressBookService;
import util.AddressBookDB;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AddressBookDB addressBookDB = new AddressBookDB();

        PersonRepository personRepository = new PersonRepository(addressBookDB, 0l);
        CompanyRepository companyRepository = new CompanyRepository(addressBookDB, 0l);
        ContactRepository contactRepository = new ContactRepository(addressBookDB, 0l);
        AddressBookService addressBookService = new AddressBookService(personRepository, companyRepository, contactRepository);

        Scanner scanner = new Scanner(System.in);
        AddressBookController addressBookController = new AddressBookController(addressBookService, scanner);

        addressBookController.presentMenu();
    }

}

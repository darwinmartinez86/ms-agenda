package controller;

import entity.Company;
import entity.Person;
import service.AddressBookService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddressBookController {

    private static final String MENU = "1. Agregar Persona" +
            "\n2. Agregar Empresa" +
            "\n3. Agregar Contacto" +
            "\n4. Buscar Persona por Nombre" +
            "\n5. Buscar Empresa por Nombre" +
            "\n6. Buscar Empresa por Ciudad" +
            "\n7. Buscar Empresa por Nombre en Ciudades" +
            "\n8. Buscar Contacto por Nombre de Persona" +
            "\n9. Buscar Contacto por Nombre de Empresa" +
            "\n10. Buscar Contacto por Ciudad" +
            "\n11. Buscar Contacto por Nombre de Empresa en Ciudades" +
            "\n12. Salir";

    private static final String SUBMENU = "Para la creacion de un contacto debe tener los datos de una Persona y una Empresa" +
            "\n1. Conozco el Id de la Persona y de la Empresa" +
            "\n2. Conozco el Id de la Persona pero no de la Empresa" +
            "\n3. Conozco el Id de la Empresa pero no de la Persona" +
            "\n4. Deseo crear una persona y empresa nueva" +
            "\n5. Volver al menu anterior";

    private Scanner scanner;
    private AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService, Scanner scanner) {
        this.addressBookService = addressBookService;
        this.scanner = scanner;
    }

    public void presentMenu() {

        boolean exit = false;

        while (!exit) {
            System.out.println(MENU);
            System.out.print("Seleccione una opción: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addPersonFromConsole();
                    break;
                case "2":
                    addCompanyFromConsole();
                    break;
                case "3":
                    addContactMenu();
                    break;
                case "4":
                    searchPersonByNameFromConsole();
                    break;
                case "5":
                    searchCompanyByNameFromConsole();
                    break;
                case "6":
                    searchCompanyByCityFromConsole();
                    break;
                case "7":
                    searchCompanyByNameInCitiesFromConsole();
                    break;
                case "8":
                    searchContactByPersonNameFromConsole();
                    break;
                case "9":
                    searchContactByCompanyNameFromConsole();
                    break;
                case "10":
                    searchContactByCompanyCityFromConsole();
                    break;
                case "11":
                    searchContactByCompanyNameInCitiesFromConsole();
                    break;
                case "12":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }

    }

    private void addContactMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println(SUBMENU);
            System.out.print("Seleccione una opción: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addContactFromConsole();
                    break;
                case "2":
                    addContactAndCompanyFromConsole();
                    break;
                case "3":
                    addContactAndPersonFromConsole();
                    break;
                case "4":
                    addContactAndPersonAndCompanyFromConsole();
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void addPersonFromConsole() {
        Person person = getPerson();
        addressBookService.addPersonFromConsole(person);
    }

    private void addCompanyFromConsole() {
        Company company = getCompany();
        addressBookService.addCompanyFromConsole(company);
    }

    private void searchPersonByNameFromConsole() {
        System.out.print("Ingrese el nombre a buscar: ");
        String name = scanner.nextLine();
        addressBookService.searchPersonByNameFromConsole(name);
    }

    private void searchCompanyByNameFromConsole() {
        System.out.print("Ingrese el nombre a buscar: ");
        String name = scanner.nextLine();
        addressBookService.searchCompanyByNameFromConsole(name);
    }

    private void searchCompanyByCityFromConsole() {
        System.out.print("Ingrese la ciudad a buscar: ");
        String city = scanner.nextLine();
        addressBookService.searchCompanyByCityFromConsole(city);
    }

    private void searchCompanyByNameInCitiesFromConsole() {
        System.out.print("Ingrese el nombre a buscar: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese las ciudades separadas por comas: ");
        String citiesInput = scanner.nextLine();
        List<String> citiesToSearch = Arrays.asList(citiesInput.split(","));

        addressBookService.searchByNameInCitiesFromConsole(name, citiesToSearch);
    }

    private void searchContactByCompanyNameFromConsole() {
        System.out.print("Ingrese el nombre de la empresa a buscar: ");
        String name = scanner.nextLine();
        addressBookService.searchContactByCompanyName(name);
    }

    private void searchContactByPersonNameFromConsole() {
        System.out.print("Ingrese el nombre de la persona a buscar: ");
        String name = scanner.nextLine();
        addressBookService.searchContactByPersonName(name);
    }

    private void searchContactByCompanyCityFromConsole() {
        System.out.print("Ingrese la ciudad a buscar: ");
        String city = scanner.nextLine();
        addressBookService.searchContactByCity(city);
    }

    private void searchContactByCompanyNameInCitiesFromConsole() {
        System.out.print("Ingrese el nombre de la empresa a buscar: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese las ciudades separadas por comas: ");
        String citiesInput = scanner.nextLine();
        List<String> citiesToSearch = Arrays.asList(citiesInput.split(","));

        addressBookService.searchContactByCompanyNameInCitiesFromConsole(name, citiesToSearch);
    }

    private void addContactAndPersonAndCompanyFromConsole() {
        Company company = getCompany();
        Person person = getPerson();

        System.out.print("Ingrese el rol de la persona: ");
        String rol = scanner.nextLine();
        try {
            addressBookService.addContactFromConsole(company, person, rol);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addContactAndCompanyFromConsole() {

        Company company = getCompany();
        Long personId = getPersonId();

        System.out.print("Ingrese el rol de la persona: ");
        String rol = scanner.nextLine();
        try {
            addressBookService.addContactFromConsole(company, personId, rol);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addContactAndPersonFromConsole() {

        Long companyId = getCompanyId();
        Person person = getPerson();

        System.out.print("Ingrese el rol de la persona: ");
        String rol = scanner.nextLine();
        try {
            addressBookService.addContactFromConsole(companyId, person, rol);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addContactFromConsole() {

        Long companyId = getCompanyId();
        Long personId = getPersonId();

        System.out.print("Ingrese el rol de la persona: ");
        String rol = scanner.nextLine();
        try {
            addressBookService.addContactFromConsole(companyId, personId, rol);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private Person getPerson() {
        Person person = new Person();
        do {
            try {
                System.out.print("Ingrese el nombre de la persona: ");
                person.setName(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (person.getName() == null);

        do {
            try {
                System.out.print("Ingrese el teléfono de la persona: ");
                person.setPhone(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (person.getPhone() == null);

        do {
            try {
                System.out.print("Ingrese el correo electrónico de la persona: ");
                person.setEmail(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (person.getEmail() == null);
        return person;
    }

    private Company getCompany() {
        Company company = new Company();
        do {
            try {
                System.out.print("Ingrese el nombre de la empresa: ");
                company.setName(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (company.getName() == null);

        do {
            try {
                System.out.print("Ingrese la ciudad de la empresa: ");
                company.setCity(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (company.getCity() == null);
        return company;
    }

    private Long getCompanyId() {
        Long companyId = null;
        do {
            try {
                System.out.print("Ingrese el ID de la empresa: ");
                companyId = Long.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido para el ID de la empresa.");
            }
        } while (companyId == null);
        return companyId;
    }

    private Long getPersonId() {
        Long personId = null;
        do {
            try {
                System.out.print("Ingrese el ID de la persona: ");
                personId = Long.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido para el ID de la persona.");
            }
        } while (personId == null);
        return personId;
    }
}

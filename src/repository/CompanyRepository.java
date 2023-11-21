package repository;

import entity.Company;
import util.AddressBookDB;

import java.util.List;

public class CompanyRepository {

    private AddressBookDB addressBookDB;
    private Long id;

    public CompanyRepository(AddressBookDB addressBookDB, Long id) {
        this.addressBookDB = addressBookDB;
        this.id = id;
    }

    public String addCompany(Company company) {
        company.setId(++id);
        addressBookDB.addCompany(company);
        return company.toString();
    }

    public List<Company> searchByName(String name) {
        return addressBookDB.searchCompanyByName(name);
    }

    public List<Company> searchByCity(String name) {
        return addressBookDB.searchCompanyByCity(name);
    }

    public List<Company> searchByNameInCities(String name, List<String> cities) {
        return addressBookDB.searchCompanyByNameInCities(name,cities);
    }

    public Company getById(Long companyId) {
        return addressBookDB.searchCompanyById(companyId);
    }

    public boolean exists(Company company) {
        return addressBookDB.companyExists(company);
    }
}

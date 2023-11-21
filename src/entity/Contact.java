package entity;

import java.util.Objects;

public class Contact {

    private Long id;
    private Person person;
    private Company company;
    private String role;

    public Contact() {
    }

    public Contact(Person person, Company company, String role) {
        this.id = id;
        this.person = person;
        this.company = company;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", Persona: " + person.toString() +
                ", Empresa: " + company.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(person, contact.person) && Objects.equals(company, contact.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, company);
    }
}

package entity;

import java.util.Objects;

public class Company {

    private Long id;
    private String name;
    private String city;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches("^[a-zA-Z]{3,}(\\s[a-zA-Z]+)*$")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres, debe contener solo letras, y evitar espacios en blanco extras al inicio o al final");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.matches("^[a-zA-Z]{3,}(\\s[a-zA-Z]+)*$")) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("La ciudad debe tener al menos 3 caracteres, debe contener solo letras, y evitar espacios en blanco extras al inicio o al final");
        }
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", Nombre: '" + name + '\'' +
                ", Ciudad: '" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) && Objects.equals(city, company.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
}

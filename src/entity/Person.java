package entity;

import java.util.Objects;
import java.util.regex.Pattern;

public class Person {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private Long id;
    private String name;
    private String phone;
    private String email;

    public Person() {
    }

    public Person(String name, String phone, String email) {
        setName(name);
        setPhone(phone);
        setEmail(email);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.matches("^[0-9]{8,}$")) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("El número de teléfono debe contener solo dígitos y tener al menos 8 caracteres. Asegúrese de eliminar cualquier espacio, guión o carácter no numérico.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido. Asegúrese de seguir el formato estándar, por ejemplo: usuario@dominio.com");
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", Nombre: '" + name + '\'' +
                ", Telefono: '" + phone + '\'' +
                ", Correo: '" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(phone, person.phone) || Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }
}

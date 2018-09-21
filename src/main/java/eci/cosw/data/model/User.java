package eci.cosw.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    public User() {
    }

    public User(String id, String name, String email) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, name='%s', email='%s']",
                id, name, email);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNameame(String firstName) {
        this.name = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String lastName) {
        this.email = lastName;
    }
}
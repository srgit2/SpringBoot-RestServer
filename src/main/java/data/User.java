package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.javafx.beans.IDProperty;

public class User {

    private int ID;

    public User(@JsonProperty(value = "ID") int ID, @JsonProperty(value="Name") String name, @JsonProperty(value = "Email") String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String name;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

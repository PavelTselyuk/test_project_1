package api.entities;

public class Name{
    public String firstname;
    public String lastname;

    public Name(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Name(){}

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
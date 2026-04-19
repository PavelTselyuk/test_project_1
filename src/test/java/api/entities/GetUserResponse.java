package api.entities;

public class GetUserResponse {
    private Address address;
    private Integer id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String phone;
    private Integer __v;

    public GetUserResponse() {}

    public GetUserResponse(Address address, Integer id, String email, String username, String password, Name name, String phone, Integer __v) {
        this.address = address;
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.__v = __v;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Name getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Integer get__v() {
        return __v;
    }
}

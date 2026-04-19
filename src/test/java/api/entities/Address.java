package api.entities;

public class Address{
    public Geolocation geolocation;
    public String city;
    public String street;
    public Integer number;
    public String zipcode;

    public Address(Geolocation geolocation, String city, String street, Integer number, String zipcode) {
        this.geolocation = geolocation;
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
    }
    public Address() {}

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getZipcode() {
        return zipcode;
    }
}
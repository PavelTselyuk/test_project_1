package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class User {

    String firstName;
    String lastName;
    String email;
    String gender;
    String mobileNumber;
    String dateOfBirth;
    String[] subjects;
    String[] hobbies;
    String currentAddress;
    String state;
    String city;

    public String getFirstName(){ return firstName;}
    public String getLastName(){ return lastName;}
    public String getEmail(){ return email;}
    public String getGender(){ return gender;}
    public String getMobileNumber(){ return mobileNumber;}
    public String getDateOfBirth(){ return dateOfBirth;}
    public String[] getSubjects(){ return subjects;}
    public String[] getHobbies(){ return hobbies;}
    public String getCurrentAddress(){ return currentAddress;}
    public String getState(){ return state;}
    public String getCity(){ return city;}

    public static class Builder {

        private String firstName;
        private String lastName;
        private String email;
        private String gender;
        private String mobileNumber;
        private String dateOfBirth;
        private String[] subjects;
        private String[] hobbies;
        private String currentAddress;
        private String state;
        private String city;

        public Builder() {
        }

        public Builder(String firstName, String lastName, String email, String gender, String mobileNumber, String dateOfBirth, String[] subjects, String[] hobbies, String currentAddress, String state, String city) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.gender = gender;
            this.mobileNumber = mobileNumber;
            this.dateOfBirth = dateOfBirth;
            this.subjects = subjects;
            this.hobbies = hobbies;
            this.currentAddress = currentAddress;
            this.state = state;
            this.city = city;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return Builder.this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return Builder.this;
        }

        public Builder email(String email){
            this.email = email;
            return Builder.this;
        }

        public Builder gender(String gender){
            this.gender = gender;
            return Builder.this;
        }

        public Builder mobileNumber(String mobileNumber){
            this.mobileNumber = mobileNumber;
            return Builder.this;
        }

        public Builder dateOfBirth(int day, int month, int year){
            LocalDate date = LocalDate.of(2000, 1, 16);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
            String formattedDate = formatter.format(date);
            this.dateOfBirth = dateOfBirth;
            return Builder.this;
        }

        public Builder subjects(String[] subjects){
            this.subjects = subjects;
            return Builder.this;
        }

        public Builder hobbies(String[] hobbies){
            this.hobbies = hobbies;
            return Builder.this;
        }

        public Builder currentAddress(String currentAddress){
            this.currentAddress = currentAddress;
            return Builder.this;
        }

        public Builder state(String state){
            this.state = state;
            return Builder.this;
        }

        public Builder city(String city){
            this.city = city;
            return Builder.this;
        }

        public User build() {
            return new User(this);
        }
    }

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.gender = builder.gender;
        this.mobileNumber = builder.mobileNumber;
        this.dateOfBirth = builder.dateOfBirth;
        this.subjects = builder.subjects;
        this.hobbies = builder.hobbies;
        this.currentAddress = builder.currentAddress;
        this.state = builder.state;
        this.city = builder.city;
    }


    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2000, 1, 16);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        String formattedDate = formatter.format(date);
        System.out.println(formattedDate);
        System.out.println(date);
    }
}

enum Gender{
    MALE, FEMALE, OTHER
}


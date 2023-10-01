package model;

import java.util.regex.Pattern;

public class Customer {
    private  String firstName;
    private  String lastName;
    private  String email;

    public Customer( String firstName, String lastName, String email) {
        this.checkEmail(email);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private void checkEmail(final String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Email does not  match our regular expression for email, name@domain.com");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
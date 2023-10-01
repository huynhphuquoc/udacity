package model;

import java.util.regex.Pattern;

public class Customer {
    private  String firstName;
    private  String lastName;
    private  String email;
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";

    public Customer( String firstName, String lastName, String email) {
        this.checkEmail(email);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    private void checkEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Email does not  match our regular expression for email, name@domain.com");
        }
    }

}
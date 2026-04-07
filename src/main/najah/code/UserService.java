package main.najah.code;

public class UserService {

    // Simple email validation: we check if the email is not null and contains both '@' and '.'
    public boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    // Simple authentication: we check if the username is "admin" and the password is "1234"
    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "1234".equals(password);
    }
}

package Atm;

public class Manager extends Person {
    private String username;
    private String password;

   
    public Manager(String id, String name, String gender, String username, String password) {
        super(id, name, gender); 
        validateUsername(username);
        validatePassword(password);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    private void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long.");
        }
    }
}

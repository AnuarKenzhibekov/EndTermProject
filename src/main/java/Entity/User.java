package Entity;

public class User {
    // Static field for user ID (shared among all instances)
    private static int userID;

    // Instance fields for user's name and email
    private String userName;
    private String email;

    // Constructor to initialize user with name and email
    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    // Getter method for static user ID
    public static int getUserID() {
        return userID;
    }

    // Setter method for static user ID
    public void setUserID(int userID) {
        User.userID = userID;  // Since userID is static, it should be accessed via the class name
    }

    // Getter method for user's name
    public String getUserName() {
        return userName;
    }

    // Setter method for user's name
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter method for user's email
    public String getEmail() {
        return email;
    }

    // Setter method for user's email
    public void setEmail(String email) {
        this.email = email;
    }
}

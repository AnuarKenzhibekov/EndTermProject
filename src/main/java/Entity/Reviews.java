package Entity;

public class Reviews {
    // Fields representing review properties
    private String userName;
    private String feedback;
    private int grade;

    // Constructor to initialize review details
    public Reviews(String userName, String feedback, int grade) {
        this.userName = userName;
        this.feedback = feedback;
        this.grade = grade;
    }

    // Getter method for user name
    public String getUserName() {
        return userName;
    }

    // Getter method for feedback
    public String getFeedback() {
        return feedback;
    }

    // Getter method for grade
    public int getGrade() {
        return grade;
    }
}

package Entity;

public class Reviews {
    private String userName;
    private String feedback;
    private int grade;

    public Reviews(String userName, String feedback, int grade) {
        this.userName = userName;
        this.feedback = feedback;
        this.grade = grade;
    }

    public String getUserName() {
        return userName;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getGrade() {
        return grade;
    }
}

package org.example;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Reviews {
    private String userId;
    private String feedback;
    private int rating;

    public Reviews(String userId, String feedback, int rating) {
        this.userId = userId;
        this.feedback = feedback;
        this.rating = rating;
    }


    public String getUserId() {
        return userId;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getRating() {
        return rating;
    }
}



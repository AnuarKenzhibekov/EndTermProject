package Entity;

public class Movies {
    // Fields representing movie properties
    private int movieId;
    private String title;
    private String genre;
    private String duration;

    // Constructor to initialize movie details
    public Movies(String title, String genre, String duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    // Getter method for movie ID
    public int getMovieId() {
        return movieId;
    }

    // Setter method for movie ID
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    // Getter method for movie title
    public String getTitle() {
        return title;
    }

    // Setter method for movie title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method for movie genre
    public String getGenre() {
        return genre;
    }

    // Setter method for movie genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Getter method for movie duration
    public String getDuration() {
        return duration;
    }

    // Setter method for movie duration
    public void setDuration(String duration) {
        this.duration = duration;
    }
}

package Entity;

public class Movies {
    private static int movieId;
    private static String title;
    private String genre;
    private String duration;

    public Movies(String title, String genre, String duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }


    public static int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public static String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

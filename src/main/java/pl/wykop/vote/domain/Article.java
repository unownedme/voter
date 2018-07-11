package pl.wykop.vote.domain;

public class Article implements Activity, Voteable {
    private UserProfile author;
    private String uid;
    private String text;
    private int score;

    public Article(String uid, UserProfile author, String text, int score) {
        this.uid = uid;
        this.author = author;
        this.text = text;
        this.score = score;
    }

    public String uid() {
        return uid;
    }

    @Override
    public void applyVote(Vote vote) {
        this.score = vote.applyToScore(this.score);
    }

    @Override
    public int score() {
        return score;
    }
}

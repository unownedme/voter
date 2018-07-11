package pl.wykop.vote.domain;

public class Vote {

    public enum Type {
        UP(1), DOWN(-1), NONE(0);

        int value;

        Type(int value) {
            this.value = value;
        }
    }

    private String author;

    private String activityUid;
    private Type type;

    //transient
    private int scoreToApply = 0;


    public Vote(String author, String activityUid) {
        this.author = author;
        this.activityUid = activityUid;
        this.type = Type.NONE;
    }

    public void setTo(Type type) {
        if (type.equals(this.type)) {
            throw new IllegalStateException("Can't change to same vote");
        }
        this.scoreToApply = -this.type.value + type.value;
        this.type = type;
    }

    public String authorUsername() {
        return author;
    }

    public int scoreToApply() {
        return scoreToApply;
    }

    public String activityUid() {
        return activityUid;
    }
}

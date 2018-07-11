package pl.wykop.vote.domain;

public class UserProfile {
    private final String username;
    private final String password;

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }
}

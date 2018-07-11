package pl.wykop.vote.api;

class VoteCommand {

    public enum Type {
        UP, DOWN
    }

    private final String username;
    private final String activityUid;
    private final Type type;

    VoteCommand(String username, String activityUid, Type type) {
        this.username = username;
        this.activityUid = activityUid;
        this.type = type;
    }

    String getUsername() {
        return username;
    }

    String getActivityUid() {
        return activityUid;
    }

    Type getType() {
        return type;
    }
}

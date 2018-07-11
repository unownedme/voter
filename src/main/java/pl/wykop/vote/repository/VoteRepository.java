package pl.wykop.vote.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import pl.wykop.vote.domain.Vote;

public class VoteRepository {
    private final Map<String, Vote> data = new HashMap<>();

    private String hash(String activityUid, String username) {
        return activityUid + username;
    }

    public Optional<Vote> find(String uid, String username) {
        return Optional.ofNullable(data.get(hash(uid, username)));
    }

    public void store(Vote vote) {
        data.put(hash(vote.activityUid(), vote.authorUsername()), vote);
    }

    public Vote createVoteFor(String activityUid, String username) {
        return new Vote(username, activityUid);
    }
}

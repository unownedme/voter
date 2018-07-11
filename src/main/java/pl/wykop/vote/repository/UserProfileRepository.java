package pl.wykop.vote.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import pl.wykop.vote.domain.UserProfile;

public class UserProfileRepository {
    private final Map<String, UserProfile> data = new HashMap<>();

    public Optional<UserProfile> find(String username) {
        return Optional.ofNullable(data.get(username));
    }

    public void store(UserProfile userProfile) {
        data.put(userProfile.username(), userProfile);
    }
}

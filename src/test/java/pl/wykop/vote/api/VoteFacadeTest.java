package pl.wykop.vote.api;

import org.junit.Before;
import org.junit.Test;
import pl.wykop.vote.domain.Article;
import pl.wykop.vote.domain.UserProfile;
import pl.wykop.vote.repository.ArticleRepository;
import pl.wykop.vote.repository.UserProfileRepository;
import pl.wykop.vote.repository.VoteRepository;

import static org.junit.Assert.*;

public class VoteFacadeTest {
    private UserProfileRepository userProfileRepository;
    private ArticleRepository articleRepository;
    private VoteRepository voteRepository;
    private VoteFacade objectUnderTest;

    @Before
    public void setUp() {
        userProfileRepository = new UserProfileRepository();
        UserProfile userProfile = new UserProfile("someuser", "testpass");
        userProfileRepository.store(userProfile);
        articleRepository = new ArticleRepository();
        articleRepository.store(new Article("ar1", userProfile,"", 0));
        voteRepository = new VoteRepository();
        objectUnderTest = new VoteFacade(userProfileRepository, articleRepository, voteRepository);
    }

    @Test
    public void shouldVoteUp() {
        //given
        VoteCommand voteCommand = new VoteCommand("someuser", "ar1", VoteCommand.Type.UP);
        //when
        int result = objectUnderTest.vote(voteCommand);
        //then
        assertEquals(1, result);
    }

    @Test
    public void shouldVoteDown() {
        //given
        VoteCommand voteCommand = new VoteCommand("someuser", "ar1", VoteCommand.Type.DOWN);
        //when
        int result = objectUnderTest.vote(voteCommand);
        //then
        assertEquals(-1, result);
    }

    @Test
    public void shouldRevoteUp() {
        //given
        objectUnderTest.vote(new VoteCommand("someuser", "ar1", VoteCommand.Type.DOWN));
        VoteCommand voteCommand = new VoteCommand("someuser", "ar1", VoteCommand.Type.UP);
        //when
        int result = objectUnderTest.vote(voteCommand);
        //then
        assertEquals(1, result);
    }

    @Test
    public void shouldRevoteRevoteUp() {
        //given
        objectUnderTest.vote(new VoteCommand("someuser", "ar1", VoteCommand.Type.UP));
        objectUnderTest.vote(new VoteCommand("someuser", "ar1", VoteCommand.Type.DOWN));
        VoteCommand voteCommand = new VoteCommand("someuser", "ar1", VoteCommand.Type.UP);
        //when
        int result = objectUnderTest.vote(voteCommand);
        //then
        assertEquals(1, result);
    }

    @Test
    public void shouldRevoteDown() {
        //given
        objectUnderTest.vote(new VoteCommand("someuser", "ar1", VoteCommand.Type.UP));
        VoteCommand voteCommand = new VoteCommand("someuser", "ar1", VoteCommand.Type.DOWN);
        //when
        int result = objectUnderTest.vote(voteCommand);
        //then
        assertEquals(-1, result);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowWhenDoubleRevoteDown() {
        //given
        objectUnderTest.vote(new VoteCommand("someuser", "ar1", VoteCommand.Type.DOWN));
        VoteCommand voteCommand = new VoteCommand("someuser", "ar1", VoteCommand.Type.DOWN);
        //when
        int result = objectUnderTest.vote(voteCommand);
        //then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenUserNotFound() {
        //given
        VoteCommand voteCommand = new VoteCommand("NONEUSRE", "ar1", VoteCommand.Type.DOWN);
        //when
        int result = objectUnderTest.vote(voteCommand);
        //then
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenActivityNotFound() {
        //given
        VoteCommand voteCommand = new VoteCommand("someuser", "NONEACT", VoteCommand.Type.UP);
        //when
        int result = objectUnderTest.vote(voteCommand);
        //then
        fail();
    }
}

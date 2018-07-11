package pl.wykop.vote.api;

import pl.wykop.vote.domain.Article;
import pl.wykop.vote.domain.Vote;
import pl.wykop.vote.repository.ArticleRepository;
import pl.wykop.vote.repository.UserProfileRepository;
import pl.wykop.vote.repository.VoteRepository;

public class VoteFacade {
    private final UserProfileRepository userProfileRepository;
    private final ArticleRepository articleRepository;
    private final VoteRepository voteRepository;

    public VoteFacade(UserProfileRepository userProfileRepository, ArticleRepository articleRepository, VoteRepository voteRepository) {
        this.userProfileRepository = userProfileRepository;
        this.articleRepository = articleRepository;
        this.voteRepository = voteRepository;
    }

    public int vote(VoteCommand voteCommand) {
        userProfileRepository.find(voteCommand.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Article article = articleRepository.find(voteCommand.getActivityUid())
                .orElseThrow(() -> new IllegalArgumentException("Activity not found"));
        Vote vote = voteRepository.find(voteCommand.getActivityUid(), voteCommand.getUsername())
                .orElseGet(() -> voteRepository.createVoteFor(voteCommand.getActivityUid(), voteCommand.getUsername()));

        vote.setTo(Vote.Type.valueOf(voteCommand.getType().name()));
        article.applyVote(vote.scoreToApply());

        articleRepository.store(article);
        voteRepository.store(vote);
        return article.score();
    }

}

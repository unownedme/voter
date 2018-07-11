package pl.wykop.vote.domain;

interface Voteable {

    void applyVote(Vote vote);

    int score();
}

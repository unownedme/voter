package pl.wykop.vote.domain;

interface Voteable {

    void applyVote(int vote);

    int score();
}

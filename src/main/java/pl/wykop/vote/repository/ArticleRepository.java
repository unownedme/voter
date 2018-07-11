package pl.wykop.vote.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import pl.wykop.vote.domain.Article;

public class ArticleRepository {

    private final Map<String, Article> data = new HashMap<>();


    public Optional<Article> find(String uid) {
        return Optional.ofNullable(data.get(uid));
    }

    public void store(Article article) {
        data.put(article.uid(), article);
    }

}

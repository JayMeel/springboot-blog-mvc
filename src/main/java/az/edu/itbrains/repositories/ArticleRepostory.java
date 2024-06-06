package az.edu.itbrains.repositories;

import az.edu.itbrains.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepostory extends JpaRepository<Article,Long> {
}

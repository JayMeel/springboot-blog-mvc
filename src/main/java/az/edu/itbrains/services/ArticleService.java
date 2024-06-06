package az.edu.itbrains.services;

import az.edu.itbrains.dtos.ArticleDtos.*;
import az.edu.itbrains.models.Article;

import java.util.List;

public interface ArticleService {
    void CreateArticle(ArticleCreateDto articleCreateDto);

    List<ArticleDashboardDto> getDashboardArticles();

    ArticleUpdateDto getUpdateArticles(Long id);
    void updateArticle(ArticleUpdateDto articleUpdateDto, Long id);
    void removeArticle(Long id);
    List<ArticleHomeDto> getHomeArticles();
    ArticleDetailDto getDetail (Long id);
}

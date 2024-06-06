package az.edu.itbrains.services.impls;

import az.edu.itbrains.dtos.ArticleDtos.*;
import az.edu.itbrains.models.Article;
import az.edu.itbrains.models.Category;
import az.edu.itbrains.repositories.ArticleRepostory;
import az.edu.itbrains.services.ArticleService;
import az.edu.itbrains.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepostory articleRepostory;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void CreateArticle(ArticleCreateDto articleCreateDto) {
        Category findCategory = categoryService.findCategoryById(articleCreateDto.getCategoryId());
        Article article = new Article();
        article.setTitle(articleCreateDto.getTitle());
        article.setDescription(articleCreateDto.getDescription());
        article.setCreatedDate(new Date());
        article.setPhotoUrl(articleCreateDto.getPhotoUrl());
        article.setSeoUrl("");
        article.setCategory(findCategory);
        articleRepostory.save(article);
    }

    @Override
    public List<ArticleDashboardDto> getDashboardArticles() {
        List<Article> articles = articleRepostory.findAll();
        List<ArticleDashboardDto> result = articles.stream().map(article -> modelMapper
                        .map(article, ArticleDashboardDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public ArticleUpdateDto getUpdateArticles(Long id) {
        Article findArticle=articleRepostory.findById(id).orElseThrow();
        ArticleUpdateDto article =modelMapper.map(findArticle, ArticleUpdateDto.class);
        return article;
    }

    @Override
    public void updateArticle(ArticleUpdateDto articleUpdateDto, Long id) {

    }

    @Override
    public void removeArticle(Long id) {

    }

    @Override
    public List<ArticleHomeDto> getHomeArticles() {
        List<Article> articles = articleRepostory.findAll();
        List<ArticleHomeDto> result = articles.stream().map(article -> modelMapper.map(article, ArticleHomeDto.class)).collect(Collectors.toList());
        return result;

    }

    @Override
    public ArticleDetailDto getDetail(Long id) {
        Article article = articleRepostory.findById(id).orElseThrow();
        ArticleDetailDto result = modelMapper.map(article, ArticleDetailDto.class);
        return result;
    }
}

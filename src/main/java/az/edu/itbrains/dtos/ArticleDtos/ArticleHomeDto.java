package az.edu.itbrains.dtos.ArticleDtos;

import az.edu.itbrains.dtos.CategoryDtos.CategoryHomeDto;
import lombok.Data;

@Data
public class ArticleHomeDto {
    private Long id;
    private String title;
    private String description;
    private String photoUrl;
    private CategoryHomeDto category;
    private int viewCount;
}

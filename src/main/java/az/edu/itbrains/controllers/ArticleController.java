package az.edu.itbrains.controllers;

import az.edu.itbrains.dtos.ArticleDtos.ArticleCreateDto;
import az.edu.itbrains.dtos.ArticleDtos.ArticleDashboardDto;
import az.edu.itbrains.dtos.ArticleDtos.ArticleDetailDto;
import az.edu.itbrains.dtos.ArticleDtos.ArticleHomeDto;
import az.edu.itbrains.dtos.CategoryDtos.CategoryHomeDto;
import az.edu.itbrains.services.ArticleService;
import az.edu.itbrains.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class ArticleController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/article/detail")
public String detail(Model model) {
    return "/detail";
}
    @GetMapping("/dashboard/article")
    public String index(Model model) {
        List<ArticleDashboardDto> articles = articleService.getDashboardArticles();
        model.addAttribute("articles", articles);
        return "dashboard/article/index";
    }
    @GetMapping("/article/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        ArticleDetailDto articleDetailDto=articleService.getDetail(id);
        model.addAttribute("article", articleDetailDto);
        return "/detail";
    }

    @GetMapping("/dashboard/article/create")
    public String create(Model model) {
        List<CategoryHomeDto> categories=categoryService.getHomeCategories();
        model.addAttribute("categories", categories);
        return "/dashboard/article/create";
    }

    @PostMapping("/dashboard/article/create")
    public String create(ArticleCreateDto articleCreateDto, @RequestParam("image") MultipartFile file) throws IOException {
        UUID rand=UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY,rand+ file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        articleCreateDto.setPhotoUrl(rand+fileNames.toString());
        articleService.CreateArticle(articleCreateDto);
        return "redirect:/dashboard/article";
    }
}

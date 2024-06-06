package az.edu.itbrains.controllers;

import az.edu.itbrains.dtos.ArticleDtos.ArticleHomeDto;
import az.edu.itbrains.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        List<ArticleHomeDto> articles = articleService.getHomeArticles();
        model.addAttribute("articles", articles);
        return "/Home";
    }

@GetMapping("/contact")
public String Contact(){
        return "Contact";
}
}

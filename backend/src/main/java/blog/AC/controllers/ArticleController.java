package blog.AC.controllers;

import blog.AC.domain.dto.ArticleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @PostMapping
    public ResponseEntity<ArticleDto> addArticle(ArticleDto dto)
    {
        return null;
    }

    @PatchMapping
    public ResponseEntity<ArticleDto> modifyArticle(ArticleDto Dto)
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteArticle(Long id)
    {
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ArticleDto> getArticleByCategory(Long id)
    {
        return null;
    }
}

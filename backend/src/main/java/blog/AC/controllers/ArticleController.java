package blog.AC.controllers;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    ArticleService articleService;

    ArticleController(ArticleService articleService)
    {
        this.articleService = articleService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ArticleEntity> addArticle(@RequestBody ArticleDto dto)

    {
        return new ResponseEntity<>(articleService.addArticle(dto), HttpStatus.OK);
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<ArticleDto> patchArticle(@RequestParam Long articleId,@RequestBody ArticleDto dto)
    {
        return new ResponseEntity<>(articleService.modifyArticle(articleId, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> article(@RequestParam Long articleId)
    {
        articleService.deleteArticle(articleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
        public ResponseEntity<List<ArticleDto>> getArticles(
                @RequestParam(required = false) String user,
                @RequestParam(required = false) String category,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int limit) {
            // Implement the logic in the service layer to handle these filters
            //List<ArticleDto> articles = articleService.getArticles(user, category, page, limit);
            return null;
            //return new ResponseEntity<>(articles, HttpStatus.OK);
        }
}

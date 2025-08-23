package blog.AC.controllers;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.services.ArticleService;
import org.springframework.data.domain.Page;
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

    public ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto dto)

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
        public ResponseEntity<Page<ArticleDto>> getArticles(
                @RequestParam(required = false) String user,
                @RequestParam(required = false) String category,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int limit) {
            // Implement the logic in the service layer to handle these filters
            return new ResponseEntity<>(articleService.getArticles(user, category, page, limit), HttpStatus.OK);

            //return new ResponseEntity<>(articles, HttpStatus.OK);
        }
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long articleId) {
        ArticleDto article = articleService.getArticle(articleId);
        if (article != null) {
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/{articleId}/like")
    public ResponseEntity<Void> likeArticle(@PathVariable Long articleId) {
        articleService.likeArticle(articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


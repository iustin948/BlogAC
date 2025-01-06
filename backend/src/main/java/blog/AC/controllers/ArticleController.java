package blog.AC.controllers;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(articleService.addArticle(dto), HttpStatus.OK) ;
    }

    @PatchMapping
    public ResponseEntity<ArticleDto> patchArticle(ArticleDto Dto)
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> article(Long id)
    {
        return ResponseEntity.noContent().build();
    }


}

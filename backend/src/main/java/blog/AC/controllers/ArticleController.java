package blog.AC.controllers;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<ArticleEntity> addArticle(@RequestBody ArticleDto dto)
    {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        //entity.setCategory(dto.getCategory());
        return new ResponseEntity<>(articleService.addArticle(entity), HttpStatus.OK) ;
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

package blog.AC.services;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.entities.ArticleEntity;

public interface ArticleService {
    ArticleEntity addArticle(ArticleDto dto);
    ArticleEntity deleteArticle(Long id);
    ArticleEntity modifyArticle(ArticleEntity entity);
}

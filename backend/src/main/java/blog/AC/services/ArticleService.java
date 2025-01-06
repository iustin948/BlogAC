package blog.AC.services;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.entities.ArticleEntity;

public interface ArticleService {
    ArticleEntity addArticle(ArticleEntity entity);
    ArticleEntity deleteArticle(ArticleEntity entity);
    ArticleEntity modifyArticle(ArticleEntity entity);
}

package blog.AC.services;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.entities.ArticleEntity;

public interface ArticleService {
    ArticleEntity addArticle(ArticleDto dto);
    ArticleEntity deleteArticle(Long id);
    ArticleDto modifyArticle(Long id, ArticleDto entity);

    ArticleDto getArticle(Long id);

    ArticleDto getArticles(String user, String category, int page, int limit);
}

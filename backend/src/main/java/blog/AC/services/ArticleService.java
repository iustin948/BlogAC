package blog.AC.services;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.entities.ArticleEntity;
import org.springframework.data.domain.Page;

public interface ArticleService {
    ArticleDto addArticle(ArticleDto dto);
    ArticleEntity deleteArticle(Long id);
    ArticleDto modifyArticle(Long id, ArticleDto entity);

    ArticleDto getArticle(Long id);

    Page<ArticleDto> getArticles(String user, String category, int page, int limit);

    void likeArticle(Long id);
}

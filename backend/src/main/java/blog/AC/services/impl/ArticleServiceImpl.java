package blog.AC.services.impl;

import blog.AC.domain.entities.ArticleEntity;
import blog.AC.repositories.ArticleRepository;
import blog.AC.services.ArticleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;
    ArticleServiceImpl(ArticleRepository articleRepository)
    {
        this.articleRepository = articleRepository;
    }
    @Override
    public ArticleEntity addArticle(ArticleEntity entity) {
        return articleRepository.save(entity);
    }

    @Override
    public ArticleEntity deleteArticle(ArticleEntity entity) {
        return null;
    }

    @Override
    public ArticleEntity modifyArticle(ArticleEntity entity) {
        return null;
    }
}

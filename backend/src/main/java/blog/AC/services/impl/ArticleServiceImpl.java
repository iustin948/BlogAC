package blog.AC.services.impl;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.domain.entities.UserEntity;
import blog.AC.repositories.ArticleRepository;
import blog.AC.repositories.UserRepository;
import blog.AC.services.ArticleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;
    UserRepository userRepository;
    @Override
    public ArticleEntity addArticle(ArticleDto dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = (UserDto) auth.getPrincipal();

        UserEntity user = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //verificare daca categoria articolui este in lista de categorii a userului


        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return articleRepository.save(entity);
    }

    @Override
    public ArticleEntity deleteArticle(Long id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        articleRepository.delete(article);
        return article;
    }




    @Override
    public ArticleDto modifyArticle(Long id, ArticleDto dto) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());

        ArticleEntity savedArticle =  articleRepository.save(article);
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle(savedArticle.getTitle());
        articleDto.setContent(savedArticle.getContent());
        return articleDto;
    }

        @Override
        public ArticleDto getArticle(Long id) {
            ArticleEntity article = articleRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Article not found"));

            ArticleDto articleDto = new ArticleDto();
            articleDto.setTitle(article.getTitle());
            articleDto.setContent(article.getContent());
            return articleDto;
        }


    @Override
    public Page<ArticleDto> getArticles(String user, String category, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        UserEntity userEntity = null;
        if(user != null) userEntity = userRepository.findByEmail(user)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Page<ArticleEntity> articlesPage =
                articleRepository.findByUserAndCategory(userEntity, category, pageable);

        return articlesPage.map(article -> {
            ArticleDto dto = new ArticleDto();
            dto.setTitle(article.getTitle());
            dto.setContent(article.getContent());
            return dto;
        });
    }
}

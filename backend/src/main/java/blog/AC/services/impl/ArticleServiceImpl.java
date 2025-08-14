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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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
    public ArticleEntity modifyArticle(ArticleEntity entity) {
        return null;
    }
}

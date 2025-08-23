package blog.AC.services.impl;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.dto.UserDto;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.domain.entities.CategoryEntity;
import blog.AC.domain.entities.LikeEntity;
import blog.AC.domain.entities.UserEntity;
import blog.AC.repositories.ArticleRepository;
import blog.AC.repositories.CategoryRepository;
import blog.AC.repositories.LikeRepository;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    LikeRepository likeRepository;
    @Override
    public ArticleDto addArticle(ArticleDto dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = (UserDto) auth.getPrincipal();

        UserEntity user = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //verificare daca categoria articolui este in lista de categorii a userului
        CategoryEntity category = categoryRepository.findByName(dto.getCategory());



        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setAuthor(user);
        entity.setCategory(category);
        entity = articleRepository.save(entity);
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(entity.getId());
        articleDto.setTitle(entity.getTitle());
        articleDto.setContent(entity.getContent());
        articleDto.setAuthorId(entity.getAuthor().getId());
        articleDto.setAuthorName(entity.getAuthor().getFirstName() + " " + entity.getAuthor().getLastName());
        articleDto.setCategory(entity.getCategory().getName());
        return articleDto;
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

            // Get the current logged-in user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDto currentUser = (UserDto) auth.getPrincipal();
            UserEntity user = userRepository.findByEmail(currentUser.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Check if the current user has liked this article
            boolean userHasLiked = likeRepository.existsByArticleAndUser(article, user);
            
            ArticleDto articleDto = new ArticleDto();
            articleDto.setId(article.getId());
            articleDto.setTitle(article.getTitle());
            articleDto.setContent(article.getContent());
            articleDto.setAuthorId(article.getAuthor().getId());
            articleDto.setAuthorName(article.getAuthor().getFirstName() + " " + article.getAuthor().getLastName());
            if(article.getCategory() != null) {
                articleDto.setCategory(article.getCategory().getName());
            }
            articleDto.setLikes(article.getLikesCount());
            articleDto.setUserHasLiked(userHasLiked);
            return articleDto;
        }


    @Override
    public Page<ArticleDto> getArticles(String user, String category, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        UserEntity userEntity = null;
        CategoryEntity categoryEntity = null;
        if(user != null) userEntity = userRepository.findByEmail(user)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // If category is null, we can pass it as null to the repository method
        if (category != null) {
               categoryEntity =  categoryRepository.findByName(category);
                if(categoryEntity == null) {
                    throw new RuntimeException("Category not found");
                }

        }
        Page<ArticleEntity> articlesPage =
                articleRepository.findByUserAndCategory(userEntity, categoryEntity, pageable);

        // Get the current logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = (UserDto) auth.getPrincipal();
        UserEntity currentUserEntity = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return articlesPage.map(article -> {
            // Check if the current user has liked this article
            boolean userHasLiked = likeRepository.existsByArticleAndUser(article, currentUserEntity);
            
            ArticleDto dto = new ArticleDto();
            dto.setTitle(article.getTitle());
            dto.setContent(article.getContent());
            dto.setId(article.getId());
            dto.setAuthorId(article.getAuthor().getId());
            dto.setAuthorName(article.getAuthor().getFirstName() + " " + article.getAuthor().getLastName());
            dto.setCategory(article.getCategory().getName());
            dto.setLikes(article.getLikesCount());
            dto.setUserHasLiked(userHasLiked);
            return dto;
        });
    }

    @Override
    public void likeArticle(Long id) {
        // Get the current logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = (UserDto) auth.getPrincipal();
        UserEntity user = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the article
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // Check if the user has already liked the article
        Optional<LikeEntity> existingLike = likeRepository.findByArticleAndUser(article, user);

        if (existingLike.isPresent()) {
            // User has already liked the article, so unlike it
            likeRepository.delete(existingLike.get());
            article.setLikesCount(article.getLikesCount() - 1);
        } else {
            // User has not liked the article, so like it
            LikeEntity like = new LikeEntity(article, user);
            likeRepository.save(like);
            article.setLikesCount(article.getLikesCount() + 1);
        }

        articleRepository.save(article);
    }
}

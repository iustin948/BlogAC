package blog.AC.services.impl;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.dto.CommentDto;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.domain.entities.CommentEntity;
import blog.AC.domain.entities.UserEntity;
import blog.AC.domain.mappers.impl.CommentMapperImpl;
import blog.AC.repositories.ArticleRepository;
import blog.AC.repositories.CommentRepository;
import blog.AC.services.ArticleService;
import blog.AC.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    CommentMapperImpl commentMapper;
    ArticleRepository articleRepository;
    MyUserDetailsService myUserDetailsService;

    @Override
    public CommentDto createComment(Long articleId, String content) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(content);
        commentEntity.setArticle(articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found")));
        commentEntity.setUserId(myUserDetailsService.getLoggedInUser().getId());
        commentEntity.setCreatedAt(java.time.LocalDateTime.now());
        CommentEntity savedComment = commentRepository.save(commentEntity);
        return commentMapper.mapTo(savedComment);

    }

    @Override
    public Page<CommentDto> getComments(Long articleId, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<CommentEntity> commentsPage = commentRepository.findByArticleId(articleId, pageable);

        return commentsPage.map(comment -> commentMapper.mapTo(comment));

    }


    @Override
    public CommentDto updateComment(Long commentId, String updatedContent) {

        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentEntity.setContent(updatedContent);
        CommentEntity updatedComment = commentRepository.save(commentEntity);
        return commentMapper.mapTo(updatedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

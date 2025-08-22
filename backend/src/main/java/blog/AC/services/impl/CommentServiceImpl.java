package blog.AC.services.impl;

import blog.AC.domain.dto.ArticleDto;
import blog.AC.domain.dto.CommentCreateDto;
import blog.AC.domain.dto.CommentDto;
import blog.AC.domain.entities.ArticleEntity;
import blog.AC.domain.entities.CommentEntity;
import blog.AC.domain.entities.UserEntity;
import blog.AC.domain.mappers.impl.CommentMapperImpl;
import blog.AC.repositories.ArticleRepository;
import blog.AC.repositories.CommentRepository;
import blog.AC.services.ArticleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import blog.AC.domain.dto.UserDto;
import blog.AC.repositories.UserRepository;
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
    UserRepository userRepository;

    @Override
    public CommentDto createComment(Long articleId, CommentCreateDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setArticle(articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found")));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = (UserDto) auth.getPrincipal();

        UserEntity user = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        commentEntity.setUser(user);

        commentEntity.setCreatedAt(java.time.LocalDateTime.now());
        if (commentDto.getParentId() != null) {
            CommentEntity parentComment = commentRepository.findById(commentDto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
            commentEntity.setParent(parentComment);
        }
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

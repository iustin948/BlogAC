package blog.AC.services.impl;

import blog.AC.domain.dto.CommentDto;
import blog.AC.domain.entities.CommentEntity;
import blog.AC.domain.mappers.impl.CommentMapperImpl;
import blog.AC.repositories.ArticleRepository;
import blog.AC.repositories.CommentRepository;
import blog.AC.services.ArticleService;
import blog.AC.services.CommentService;
import lombok.AllArgsConstructor;
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
    public List<CommentDto> getComments(Long articleId, int page, int size) {


    }

    @Override
    public CommentDto updateComment(Long commentId, String updatedContent) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }
}

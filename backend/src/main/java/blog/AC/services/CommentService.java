package blog.AC.services;

import blog.AC.domain.dto.CommentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long articleId, String content);

    Page<CommentDto> getComments(Long articleId, int page, int limit);

    CommentDto updateComment(Long commentId, String updatedContent);

    void deleteComment(Long commentId);
}

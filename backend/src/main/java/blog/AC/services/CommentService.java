package blog.AC.services;

import blog.AC.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long articleId, String content);

    List<CommentDto> getComments(Long articleId, int page, int size);

    CommentDto updateComment(Long commentId, String updatedContent);

    void deleteComment(Long commentId);
}

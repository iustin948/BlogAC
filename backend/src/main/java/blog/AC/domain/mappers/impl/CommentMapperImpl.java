package blog.AC.domain.mappers.impl;

import blog.AC.domain.dto.CommentDto;
import blog.AC.domain.entities.CommentEntity;
import blog.AC.domain.mappers.Mapper;
import blog.AC.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentMapperImpl implements Mapper<CommentEntity, CommentDto> {

    CommentRepository commentRepository;
    @Override
    public CommentDto mapTo(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }
        return new CommentDto(
                commentEntity.getId(),
                commentEntity.getContent(),
                commentEntity.getCreatedAt().toString(),
                commentEntity.getUserId(),
                commentEntity.getArticle().getId(),
                commentEntity.getParent() != null ? commentEntity.getParent().getId() : null
        );
    }

    @Override
    public CommentEntity mapFrom(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDto.getId());
        commentEntity.setContent(commentDto.getContent());
        // Assuming createdAt is set elsewhere, e.g., in the service layer
        commentEntity.setUserId(commentDto.getUserId());
        commentEntity.setArticle(commentRepository.findArticleById(commentDto.getArticleId()));
        commentEntity.setParent(commentRepository.findById(commentDto.getParentId()).orElse(null));
        return commentEntity;
    }
}

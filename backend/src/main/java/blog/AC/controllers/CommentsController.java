package blog.AC.controllers;

import blog.AC.domain.dto.CommentCreateDto;
import blog.AC.domain.dto.CommentDto;
import blog.AC.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/article/{articleId}/comments")
public class CommentsController {

    CommentService commentsService;

    @GetMapping
    public ResponseEntity<Page<CommentDto>> getComments(@PathVariable Long articleId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int limit) {
        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(commentsService.getComments(articleId,page,limit), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<CommentDto> addComment(@PathVariable Long articleId,
                                                 @RequestBody CommentCreateDto commentDto) {
        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(commentsService.createComment(articleId, commentDto), HttpStatus.CREATED);

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        // Business logic to be implemented in the service layer
        commentsService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody String updatedContent) {

        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(commentsService.updateComment(commentId,updatedContent), HttpStatus.OK);
    }
}
package blog.AC.controllers;

import blog.AC.domain.dto.CommentDto;
import blog.AC.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/article/{articleId}/comments")
public class CommentsController {

    CommentService commentsService;

    @GetMapping
    public ResponseEntity<Void> getComments(@PathVariable Long articleId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int limit) {
        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping()
    public ResponseEntity<CommentDto> addComment(@PathVariable Long articleId,
                                                 @RequestBody String content) {
        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(commentsService.createComment(articleId, content), HttpStatus.CREATED);

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long articleId, @PathVariable Long commentId) {

        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
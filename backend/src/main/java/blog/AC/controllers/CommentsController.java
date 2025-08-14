package blog.AC.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles/{articleId}/comments")
public class CommentsController {

    @GetMapping
    public ResponseEntity<Void> getComments(@PathVariable Long articleId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int limit) {
        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping
    public ResponseEntity<Void> addComment(@PathVariable Long articleId) {
        // Business logic to be implemented in the service layer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
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
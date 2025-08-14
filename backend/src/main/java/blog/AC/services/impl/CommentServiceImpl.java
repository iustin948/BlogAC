package blog.AC.services.impl;

import blog.AC.repositories.CommentRepository;
import blog.AC.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
}

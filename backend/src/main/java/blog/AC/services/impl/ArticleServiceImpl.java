package blog.AC.services.impl;

import blog.AC.repositories.ArticleRepository;
import blog.AC.services.ArticleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;

}

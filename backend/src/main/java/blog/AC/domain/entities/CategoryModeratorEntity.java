package blog.AC.domain.entities;

import jakarta.persistence.*;

@Entity
public class CategoryModeratorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private CategoryEntity category;
}

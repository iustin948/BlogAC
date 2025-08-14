package blog.AC.services.impl;

import blog.AC.domain.dto.CategoryDto;
import blog.AC.domain.entities.CategoryEntity;
import blog.AC.repositories.CategoryRepository;
import blog.AC.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;


    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(entity -> new CategoryDto(entity.getId(), entity.getName()))
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(entity -> new CategoryDto(entity.getId(), entity.getName()))
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        var entity = new CategoryEntity();
        entity.setName(category.getName());
        var savedEntity = categoryRepository.save(entity);
        return new CategoryDto(savedEntity.getId(), savedEntity.getName());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto category) {
        var entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        entity.setName(category.getName());
        var updatedEntity = categoryRepository.save(entity);
        return new CategoryDto(updatedEntity.getId(), updatedEntity.getName());
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
}

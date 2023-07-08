package zw.co.kez.movies.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.kez.movies.dtos.CategoryDTO;
import zw.co.kez.movies.models.Category;
import zw.co.kez.movies.repositories.CategoryRepository;
import zw.co.kez.movies.services.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public void saveCategory(CategoryDTO categoryDTO) {

        Category category = Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();

        categoryRepository.save(category);

    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public void deleteAllCategory() {
       categoryRepository.deleteAll();
    }

    @Override
    public void deleteCategoryById(Long id) {
     categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO, Long id) {
        Category updatedCategory = categoryRepository.findById(id).orElseThrow();
        updatedCategory.setName(categoryDTO.getName() == null? updatedCategory.getName() : categoryDTO.getName());
        updatedCategory.setDescription(categoryDTO.getDescription() == null? updatedCategory.getDescription() : categoryDTO.getDescription());
        categoryRepository.save(updatedCategory);
    }
}

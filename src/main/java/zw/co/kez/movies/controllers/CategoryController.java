package zw.co.kez.movies.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.kez.movies.dtos.CategoryDTO;
import zw.co.kez.movies.models.Category;
import zw.co.kez.movies.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<String> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>("Movie " + categoryDTO.getName() + " was saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Category>> findAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteMovie() {
        categoryService.deleteAllCategory();
        return new ResponseEntity<>("All movies successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") CategoryDTO categoryDTO, Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("Category " + categoryDTO.getName() + " deleted successfully", HttpStatus.OK);
    }

}

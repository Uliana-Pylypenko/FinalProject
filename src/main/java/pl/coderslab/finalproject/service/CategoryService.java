package pl.coderslab.finalproject.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.coderslab.finalproject.dto.CategoryDTO;
import pl.coderslab.finalproject.entity.Category;
import pl.coderslab.finalproject.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return CategoryDTO.toDTO(category);
    }

    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categories = categoryRepository
                .findAll()
                .stream()
                .map(CategoryDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    public ResponseEntity<String> addCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(CategoryDTO.toEntity(categoryDTO));
        return new ResponseEntity<>("Category added", HttpStatus.OK);
    }

    public ResponseEntity<String> updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().setName(categoryDTO.getName());
            categoryRepository.save(category.get());
        }
        return new ResponseEntity<>("Category updated", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return new ResponseEntity<>("Category deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
    }
}

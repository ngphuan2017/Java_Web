package JavaWeb.SpringBoot.controller;


import JavaWeb.SpringBoot.dto.request.CreatedCategoryDTO;
import JavaWeb.SpringBoot.dto.request.UpdateCategoryDTO;
import JavaWeb.SpringBoot.dto.response.CategoryResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Category;
import JavaWeb.SpringBoot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> getAllCategory(){
        List<Category> categoryList = this.categoryService.getAllCategory();
        return new ResponseEntity(categoryList, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getCategoryPaging() {
        PageResponseDTO pageResponseDTO = categoryService.getCategoryPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "category-id") int categoryId) {
        CategoryResponseDTO CategoryResponseDTO = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(CategoryResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertCategory(@RequestBody CreatedCategoryDTO createdCategoryDTO){
        Category category = this.categoryService.createdCategory(createdCategoryDTO);
        return new ResponseEntity(category, HttpStatus.OK);
    }
    @PutMapping("/{category-id}")
    public ResponseEntity updateCategory(@PathVariable(value = "category-id") int categoryId,
                                         @RequestBody UpdateCategoryDTO updateCategoryRequestDTO) {
        CategoryResponseDTO response = categoryService.updateCategory(updateCategoryRequestDTO, categoryId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/{category-id}")
    public ResponseEntity deleteCategory(@PathVariable(value = "category-id") int categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //
    @GetMapping("/{category-id}/product")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable(value = "category-id") int categoryId) {
        CategoryResponseDTO categoryResponseDTO = categoryService.getProductByCategoryId(categoryId);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }
}

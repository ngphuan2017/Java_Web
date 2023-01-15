package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedCategoryDTO;
import JavaWeb.SpringBoot.dto.request.UpdateCategoryDTO;
import JavaWeb.SpringBoot.dto.response.CategoryResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    PageResponseDTO getCategoryPaging();
    Category createdCategory(CreatedCategoryDTO createdCategoryDTO);
    CategoryResponseDTO getCategoryById(Integer id);
    CategoryResponseDTO updateCategory(UpdateCategoryDTO requestDTO, Integer id);
    void deleteCategoryById(Integer id);
    CategoryResponseDTO getProductByCategoryId(int categoryId);
}

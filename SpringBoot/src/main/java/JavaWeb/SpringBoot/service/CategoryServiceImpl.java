package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedCategoryDTO;
import JavaWeb.SpringBoot.dto.request.UpdateCategoryDTO;
import JavaWeb.SpringBoot.dto.response.CategoryResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.dto.response.ProductResponseDTO;
import JavaWeb.SpringBoot.entity.Category;
import JavaWeb.SpringBoot.entity.Product;
import JavaWeb.SpringBoot.mapper.CategoryMapper;
import JavaWeb.SpringBoot.mapper.ProductMapper;
import JavaWeb.SpringBoot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Category> getAllCategory() {
        Iterable<Category> categoryIterable = this.categoryRepository.findAll();
        return (List)categoryIterable;
    }
    @Override
    public PageResponseDTO getCategoryPaging() {
        Pageable pageable = Pageable.ofSize(2).withPage(0);
        Page<Category> categoryPage = categoryRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Error"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(categoryPage.getNumber());
        pageResponseDTO.setSize(categoryPage.getSize());
        pageResponseDTO.setTotalPages(categoryPage.getTotalPages());
        pageResponseDTO.setTotalRecord(categoryPage.getTotalElements());
        List<CategoryResponseDTO> categoryResponseDTOS = categoryMapper.convertEntityToResponseDtos(categoryPage.getContent());
        pageResponseDTO.setData(categoryResponseDTOS);
        return pageResponseDTO;
    }
    @Override
    @Transactional
    public Category createdCategory(CreatedCategoryDTO createdCategoryDTO) {
        Category category = new Category();
        category.setCategoryName(createdCategoryDTO.getCategoryName());
        category.setCategoryImg(createdCategoryDTO.getCategoryImg());
        this.categoryRepository.save(category);
        return category;
    }
    @Override
    public CategoryResponseDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        CategoryResponseDTO categoryResponseDTOS = categoryMapper.convertEntityToResponseDto(category);
        return categoryResponseDTOS;
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(UpdateCategoryDTO requestDTO, Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        category.setCategoryName(requestDTO.getCategoryName());
        category.setCategoryImg(requestDTO.getCategoryImg());
        categoryRepository.save(category);
        CategoryResponseDTO categoryResponseDTO = categoryMapper.convertEntityToResponseDto(category);
        return categoryResponseDTO;
    }

    @Override
    @Transactional
    public void deleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        categoryRepository.delete(category);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getProductByCategoryId(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Error"));
        List<Product> productList = category.getProductList();
        List<ProductResponseDTO> productResponseDTOList = productMapper.convertEntityToResponseDtos(productList);
        CategoryResponseDTO categoryResponseDTO= categoryMapper.convertEntityToResponseDto(category);
        categoryResponseDTO.setProductResponseDTOList(productResponseDTOList);
        return categoryResponseDTO;
    }
}

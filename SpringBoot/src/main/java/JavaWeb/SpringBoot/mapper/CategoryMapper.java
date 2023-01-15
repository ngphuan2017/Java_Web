package JavaWeb.SpringBoot.mapper;

import JavaWeb.SpringBoot.dto.response.CategoryResponseDTO;
import JavaWeb.SpringBoot.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {
    public List<CategoryResponseDTO> convertEntityToResponseDtos(List<Category> categoryList){
        return categoryList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public CategoryResponseDTO convertEntityToResponseDto(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        BeanUtils.copyProperties( category, categoryResponseDTO);
        return categoryResponseDTO;
    }
}

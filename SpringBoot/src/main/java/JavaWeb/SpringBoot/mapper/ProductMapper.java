package JavaWeb.SpringBoot.mapper;

import JavaWeb.SpringBoot.dto.response.ProductResponseDTO;
import JavaWeb.SpringBoot.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    public List<ProductResponseDTO> convertEntityToResponseDtos(List<Product> productList){
        return productList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public ProductResponseDTO convertEntityToResponseDto(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties( product, productResponseDTO);
        return productResponseDTO;
    }
}

package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedProductDTO;
import JavaWeb.SpringBoot.dto.request.UpdateProductDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.dto.response.ProductResponseDTO;
import JavaWeb.SpringBoot.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    PageResponseDTO getProductPaging();
    Product createdProduct(CreatedProductDTO createdProductDTO);
    ProductResponseDTO getProductById(Integer id);

    ProductResponseDTO updateProduct(UpdateProductDTO requestDTO, Integer id);

    void deleteProductById(Integer id);
}

package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedProductDTO;
import JavaWeb.SpringBoot.dto.request.UpdateProductDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.dto.response.ProductResponseDTO;
import JavaWeb.SpringBoot.entity.Product;
import JavaWeb.SpringBoot.mapper.ProductMapper;
import JavaWeb.SpringBoot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> getAllProduct() {
        Iterable<Product> productIterable = this.productRepository.findAll();
        return (List)productIterable;
    }
    @Override
    public PageResponseDTO getProductPaging() {
        Pageable pageable = Pageable.ofSize(2).withPage(0);
        Page<Product> productPage = productRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Error"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(productPage.getNumber());
        pageResponseDTO.setSize(productPage.getSize());
        pageResponseDTO.setTotalPages(productPage.getTotalPages());
        pageResponseDTO.setTotalRecord(productPage.getTotalElements());
        List<ProductResponseDTO> productResponseDTOS = productMapper.convertEntityToResponseDtos(productPage.getContent());
        pageResponseDTO.setData(productResponseDTOS);
        return pageResponseDTO;
    }
    @Override
    @Transactional
    public Product createdProduct(CreatedProductDTO createdProductDTO) {
        Product product = new Product();
        product.setProductname(createdProductDTO.getProductname());
        product.setPrice(createdProductDTO.getPrice());
        product.setQuantity(createdProductDTO.getQuantity());
        product.setProductImg(createdProductDTO.getProductImg());
        product.setDiscontinued(createdProductDTO.getDiscontinued());
        product.setUnitSale(createdProductDTO.getUnitSale());
        this.productRepository.save(product);
        return product;
    }
    @Override
    public ProductResponseDTO getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        ProductResponseDTO productResponseDTOS = productMapper.convertEntityToResponseDto(product);
        return productResponseDTOS;
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(UpdateProductDTO requestDTO, Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        product.setProductname(requestDTO.getProductName());
        product.setPrice(requestDTO.getPrice());
        product.setQuantity(requestDTO.getQuantity());
        product.setProductImg(requestDTO.getProductImg());
        product.setDiscontinued(requestDTO.getDiscontinued());
        product.setUnitSale(requestDTO.getUnitSale());
        productRepository.save(product);
        ProductResponseDTO productResponseDTO = productMapper.convertEntityToResponseDto(product);
        return productResponseDTO;
    }

    @Override
    @Transactional
    public void deleteProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        productRepository.delete(product);
    }
}

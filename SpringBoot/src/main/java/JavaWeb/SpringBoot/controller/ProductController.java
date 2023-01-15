package JavaWeb.SpringBoot.controller;


import JavaWeb.SpringBoot.dto.request.CreatedProductDTO;
import JavaWeb.SpringBoot.dto.request.UpdateProductDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.dto.response.ProductResponseDTO;
import JavaWeb.SpringBoot.entity.Product;
import JavaWeb.SpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<?> getAllProduct(){
        List<Product> productList = this.productService.getAllProduct();
        return new ResponseEntity(productList, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getProductPaging() {
        PageResponseDTO pageResponseDTO = productService.getProductPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "product-id") int productId) {
        ProductResponseDTO ProductResponseDTO = productService.getProductById(productId);
        return new ResponseEntity<>(ProductResponseDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertProduct(@RequestBody CreatedProductDTO createdProductDTO){
        Product product = this.productService.createdProduct(createdProductDTO);
        return new ResponseEntity(product, HttpStatus.OK);
    }
    @PutMapping("/{product-id}")
    public ResponseEntity updateProduct(@PathVariable(value = "product-id") int productId,
                                        @RequestBody UpdateProductDTO updateProductRequestDTO) {
        ProductResponseDTO response = productService.updateProduct(updateProductRequestDTO, productId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @DeleteMapping("/{product-id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "product-id") int productId) {
        productService.deleteProductById(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

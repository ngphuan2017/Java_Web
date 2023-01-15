package JavaWeb.SpringBoot.controller;


import JavaWeb.SpringBoot.dto.request.CreatedCartDTO;
import JavaWeb.SpringBoot.dto.request.UpdateCartDTO;
import JavaWeb.SpringBoot.dto.response.CartResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Cart;
import JavaWeb.SpringBoot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping
    public ResponseEntity<?> getAllCart(){
        List<Cart> cartList = this.cartService.getAllCart();
        return new ResponseEntity(cartList, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getCartPaging() {
        PageResponseDTO pageResponseDTO = cartService.getCartPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{cart-id}")
    public ResponseEntity<?> getCartById(@PathVariable(value = "cart-id") int cartId) {
        CartResponseDTO CartResponseDTO = cartService.getCartById(cartId);
        return new ResponseEntity<>(CartResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{cart-id}/address")
    public ResponseEntity<?> getAddressByCartId(@PathVariable(value = "cart-id") int cartId) {
        CartResponseDTO CartResponseDTO = cartService.getAddressByCartId(cartId);
        return new ResponseEntity<>(CartResponseDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertCart(@RequestBody CreatedCartDTO createdCartDTO){
        Cart cart = this.cartService.createdCart(createdCartDTO);
        return new ResponseEntity(cart, HttpStatus.OK);
    }
    @PutMapping("/{Cart-id}")
    public ResponseEntity updateCart(@PathVariable(value = "Cart-id") int CartId,
                                     @RequestBody UpdateCartDTO updateCartRequestDTO) {
        CartResponseDTO response = cartService.updateCart(updateCartRequestDTO, CartId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/{Cart-id}")
    public ResponseEntity deleteCart(@PathVariable(value = "Cart-id") int CartId) {
        cartService.deleteCartById(CartId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

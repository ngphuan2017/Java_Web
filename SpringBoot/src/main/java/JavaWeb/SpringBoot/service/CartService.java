package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedCartDTO;
import JavaWeb.SpringBoot.dto.request.UpdateCartDTO;
import JavaWeb.SpringBoot.dto.response.CartResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCart();
    PageResponseDTO getCartPaging();

    Cart createdCart(CreatedCartDTO createdCartDTO);
    CartResponseDTO getCartById(Integer id);

    CartResponseDTO updateCart(UpdateCartDTO requestDTO, Integer id);

    void deleteCartById(Integer id);

    CartResponseDTO getAddressByCartId(int cartId);
}

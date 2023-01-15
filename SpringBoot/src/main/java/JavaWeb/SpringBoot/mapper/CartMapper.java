package JavaWeb.SpringBoot.mapper;

import JavaWeb.SpringBoot.dto.response.CartResponseDTO;
import JavaWeb.SpringBoot.entity.Cart;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {
    public List<CartResponseDTO> convertEntityToResponseDtos(List<Cart> cartList){
        return cartList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public CartResponseDTO convertEntityToResponseDto(Cart cart) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        BeanUtils.copyProperties( cart, cartResponseDTO);
        return cartResponseDTO;
    }
}

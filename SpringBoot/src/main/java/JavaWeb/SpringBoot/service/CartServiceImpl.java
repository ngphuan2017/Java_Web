package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedCartDTO;
import JavaWeb.SpringBoot.dto.request.UpdateCartDTO;
import JavaWeb.SpringBoot.dto.response.AddressResponseDTO;
import JavaWeb.SpringBoot.dto.response.CartResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Address;
import JavaWeb.SpringBoot.entity.Cart;
import JavaWeb.SpringBoot.mapper.AddressMapper;
import JavaWeb.SpringBoot.mapper.CartMapper;
import JavaWeb.SpringBoot.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<Cart> getAllCart() {
        Iterable<Cart> cartIterable = this.cartRepository.findAll();
        return (List)cartIterable;
    }
    @Override
    public PageResponseDTO getCartPaging() {
        Pageable pageable = Pageable.ofSize(2).withPage(0);
        Page<Cart> cartPage = cartRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Error"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(cartPage.getNumber());
        pageResponseDTO.setSize(cartPage.getSize());
        pageResponseDTO.setTotalPages(cartPage.getTotalPages());
        pageResponseDTO.setTotalRecord(cartPage.getTotalElements());
        List<CartResponseDTO> cartResponseDTOS = cartMapper.convertEntityToResponseDtos(cartPage.getContent());
        pageResponseDTO.setData(cartResponseDTOS);
        return pageResponseDTO;
    }
    @Override
    @Transactional
    public Cart createdCart(CreatedCartDTO createdCartDTO) {
        Cart cart = new Cart();
        cart.setPrice(createdCartDTO.getPrice());
        cart.setStatus(createdCartDTO.getStatus());
        this.cartRepository.save(cart);
        return cart;
    }
    @Override
    public CartResponseDTO getCartById(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        CartResponseDTO cartResponseDTOS = cartMapper.convertEntityToResponseDto(cart);
        return cartResponseDTOS;
    }
    @Override
    @Transactional
    public CartResponseDTO updateCart(UpdateCartDTO requestDTO, Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        cart.setUserId(requestDTO.getUserId());
        cart.setPrice(requestDTO.getPrice());
        cart.setStatus(requestDTO.getStatus());
        cartRepository.save(cart);
        CartResponseDTO cartResponseDTO = cartMapper.convertEntityToResponseDto(cart);
        return cartResponseDTO;
    }
    @Override
    @Transactional
    public void deleteCartById(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        cartRepository.delete(cart);
    }

    @Override
    public CartResponseDTO getAddressByCartId(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Error"));
        Address address = cart.getAddress();
        AddressResponseDTO addressResponseDTO = addressMapper.convertEntityToResponseDto(address);
        CartResponseDTO cartResponseDTO= cartMapper.convertEntityToResponseDto(cart);
        cartResponseDTO.setAddressResponseDTO(addressResponseDTO);
        return cartResponseDTO;
    }
}

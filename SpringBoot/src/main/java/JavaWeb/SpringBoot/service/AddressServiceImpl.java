package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedAddressDTO;
import JavaWeb.SpringBoot.dto.request.UpdateAddressDTO;
import JavaWeb.SpringBoot.dto.response.AddressResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Address;
import JavaWeb.SpringBoot.entity.Cart;
import JavaWeb.SpringBoot.entity.User;
import JavaWeb.SpringBoot.mapper.AddressMapper;
import JavaWeb.SpringBoot.mapper.CartMapper;
import JavaWeb.SpringBoot.mapper.UserMapper;
import JavaWeb.SpringBoot.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<Address> getAllAddress() {
        Iterable<Address> addressIterable = this.addressRepository.findAll();
        return (List)addressIterable;
    }
    @Override
    public PageResponseDTO getAddressPaging() {
        Pageable pageable = Pageable.ofSize(2).withPage(0);
        Page<Address> addressPage = addressRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Error"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(addressPage.getNumber());
        pageResponseDTO.setSize(addressPage.getSize());
        pageResponseDTO.setTotalPages(addressPage.getTotalPages());
        pageResponseDTO.setTotalRecord(addressPage.getTotalElements());
        List<AddressResponseDTO> addressResponseDTOS = addressMapper.convertEntityToResponseDtos(addressPage.getContent());
        pageResponseDTO.setData(addressResponseDTOS);
        return pageResponseDTO;
    }
    @Override
    @Transactional
    public Address createdAddress(CreatedAddressDTO createdAddressDTO) {
        Address address = new Address();
        address.setCountry(createdAddressDTO.getCountry());
        address.setCity(createdAddressDTO.getCity());
        address.setAddress(createdAddressDTO.getAddress());
        address.setDistrict(createdAddressDTO.getDistrict());
        address.setStreet(createdAddressDTO.getStreet());
        this.addressRepository.save(address);
        return address;
    }
    @Override
    public AddressResponseDTO getAddressById(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));

        List<User> users = address.getUser();
        Cart cart = address.getCart();

        AddressResponseDTO addressResponseDTOS = addressMapper.convertEntityToResponseDto(address);
        addressResponseDTOS.setUser(userMapper.convertEntityToResponseDtos(users));
        addressResponseDTOS.setCart(cartMapper.convertEntityToResponseDto(cart));
        return addressResponseDTOS;
    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(UpdateAddressDTO requestDTO, Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        address.setCountry(requestDTO.getCountry());
        address.setCity(requestDTO.getCity());
        address.setAddress(requestDTO.getAddress());
        address.setDistrict(requestDTO.getDistrict());
        address.setStreet(requestDTO.getStreet());
        addressRepository.save(address);
        AddressResponseDTO addressResponseDTO = addressMapper.convertEntityToResponseDto(address);
        return addressResponseDTO;
    }

    @Override
    @Transactional
    public void deleteAddressById(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));

        addressRepository.delete(address);
    }
}

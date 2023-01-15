package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedAddressDTO;
import JavaWeb.SpringBoot.dto.request.UpdateAddressDTO;
import JavaWeb.SpringBoot.dto.response.AddressResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();
    PageResponseDTO getAddressPaging();
    Address createdAddress(CreatedAddressDTO createdAddressDTO);
    AddressResponseDTO getAddressById(Integer id);

    AddressResponseDTO updateAddress(UpdateAddressDTO requestDTO, Integer id);

    void deleteAddressById(Integer id);
}

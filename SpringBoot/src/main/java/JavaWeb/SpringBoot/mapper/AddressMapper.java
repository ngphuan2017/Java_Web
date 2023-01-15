package JavaWeb.SpringBoot.mapper;

import JavaWeb.SpringBoot.dto.response.AddressResponseDTO;
import JavaWeb.SpringBoot.entity.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMapper {
    public List<AddressResponseDTO> convertEntityToResponseDtos(List<Address> addressList){
        return addressList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public AddressResponseDTO convertEntityToResponseDto(Address address) {
        AddressResponseDTO AddressResponseDTO = new AddressResponseDTO();
        BeanUtils.copyProperties( address, AddressResponseDTO);
        return AddressResponseDTO;
    }
}

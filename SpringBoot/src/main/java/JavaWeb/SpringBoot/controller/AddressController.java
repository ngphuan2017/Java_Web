package JavaWeb.SpringBoot.controller;

import JavaWeb.SpringBoot.dto.request.CreatedAddressDTO;
import JavaWeb.SpringBoot.dto.request.UpdateAddressDTO;
import JavaWeb.SpringBoot.dto.response.AddressResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Address;
import JavaWeb.SpringBoot.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    //get all
    @GetMapping
    public ResponseEntity<?> getAllAddress(){
        List<Address> addressList = this.addressService.getAllAddress();
        return new ResponseEntity(addressList, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAddressPaging() {
        PageResponseDTO pageResponseDTO = addressService.getAddressPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/{address-id}")
    public ResponseEntity<?> getAddressById(@PathVariable(value = "address-id") Integer addressId) {
        AddressResponseDTO AddressResponseDTO = addressService.getAddressById(addressId);
        return new ResponseEntity<>(AddressResponseDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertAddress(@RequestBody CreatedAddressDTO createdAddressDTO){
        Address address = this.addressService.createdAddress(createdAddressDTO);
        return new ResponseEntity(address, HttpStatus.OK);
    }
    @PutMapping("/{address-id}")
    public ResponseEntity updateAddress(@PathVariable(value = "address-id") int addressId,
                                        @RequestBody UpdateAddressDTO updateAddressRequestDTO) {
        AddressResponseDTO response = addressService.updateAddress(updateAddressRequestDTO, addressId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity deleteAddress(@PathVariable(value = "address-id") int addressId) {
        addressService.deleteAddressById(addressId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

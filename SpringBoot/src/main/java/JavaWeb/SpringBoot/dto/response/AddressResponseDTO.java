package JavaWeb.SpringBoot.dto.response;

import JavaWeb.SpringBoot.entity.Cart;
import JavaWeb.SpringBoot.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO implements Serializable {
    private Integer addressId;
    private String country;
    private String city;
    private String address;
    private String district;
    private String street;
    private List<UserResponseDTO> user;
    private CartResponseDTO cart;
}

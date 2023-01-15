package JavaWeb.SpringBoot.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String gender;
    private String email;
    private String imgLink;
    private AddressResponseDTO addressResponseDTO;
}

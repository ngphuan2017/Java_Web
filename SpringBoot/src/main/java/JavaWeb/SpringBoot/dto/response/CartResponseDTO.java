package JavaWeb.SpringBoot.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO implements Serializable {
    private Integer id;
    private Integer UserId;
    private String price;
    private Integer status;
    private AddressResponseDTO addressResponseDTO;
}

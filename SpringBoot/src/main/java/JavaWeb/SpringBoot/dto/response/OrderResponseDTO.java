package JavaWeb.SpringBoot.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO implements Serializable {
    private Integer orderId;
    private String totalPrice;
    private Integer status;
    private Integer cartId;
}

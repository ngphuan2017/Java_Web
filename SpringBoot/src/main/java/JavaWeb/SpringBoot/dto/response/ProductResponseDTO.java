package JavaWeb.SpringBoot.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO implements Serializable {
    private Integer productId;
    private String productname;
    private String price;
    private String quantity;
    private String productImg;
    private String discontinued;
    private String unitSale;
    private Integer categoryId;
}

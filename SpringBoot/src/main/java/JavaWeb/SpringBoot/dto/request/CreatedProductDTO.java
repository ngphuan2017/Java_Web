package JavaWeb.SpringBoot.dto.request;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedProductDTO {
    private String productname;
    private String price;
    private String quantity;
    private String productImg;
    private String discontinued;
    private String unitSale;
}

package JavaWeb.SpringBoot.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO {
    private String productName;
    private String price;
    private String quantity;
    private String productImg;
    private String discontinued;
    private String unitSale;
}

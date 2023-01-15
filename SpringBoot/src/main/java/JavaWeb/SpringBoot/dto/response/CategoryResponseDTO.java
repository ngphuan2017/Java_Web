package JavaWeb.SpringBoot.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO implements Serializable {
    private Integer categoryId;
    private String categoryName;
    private String categoryImg;
    private List<ProductResponseDTO> productResponseDTOList;
}

package JavaWeb.SpringBoot.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCategoryDTO {
    private String categoryName;
    private String categoryImg;
}

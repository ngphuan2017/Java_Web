package JavaWeb.SpringBoot.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCartDTO {
    private Integer UserId;
    private String price;
    private Integer status;
}

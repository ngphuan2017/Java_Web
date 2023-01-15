package JavaWeb.SpringBoot.dto.request;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedUserDTO {
    private String username;
    private String password;
    private String phone;
    private String gender;
    private String email;
    private String imgLink;
}

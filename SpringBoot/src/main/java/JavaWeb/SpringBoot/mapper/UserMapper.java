package JavaWeb.SpringBoot.mapper;

import JavaWeb.SpringBoot.dto.response.UserResponseDTO;
import JavaWeb.SpringBoot.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public List<UserResponseDTO> convertEntityToResponseDtos(List<User> userList){
        return userList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public UserResponseDTO convertEntityToResponseDto(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties( user, userResponseDTO);
        return userResponseDTO;
    }
}

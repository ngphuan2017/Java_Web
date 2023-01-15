package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedUserDTO;
import JavaWeb.SpringBoot.dto.request.UpdateUserDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.dto.response.UserResponseDTO;
import JavaWeb.SpringBoot.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    PageResponseDTO getUserPaging();

    User createdUser(CreatedUserDTO createdUserDTO);
    UserResponseDTO getUserById(Integer id);
    UserResponseDTO updateUser(UpdateUserDTO requestDTO, Integer id);
    void deleteUserById(Integer id);
}

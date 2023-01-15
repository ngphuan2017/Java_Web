package JavaWeb.SpringBoot.controller;


import JavaWeb.SpringBoot.dto.request.CreatedUserDTO;
import JavaWeb.SpringBoot.dto.request.UpdateUserDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.dto.response.UserResponseDTO;
import JavaWeb.SpringBoot.entity.User;
import JavaWeb.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> userList = this.userService.getAllUser();
        return new ResponseEntity(userList, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getUserPaging() {
        PageResponseDTO pageResponseDTO = userService.getUserPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertUser(@RequestBody CreatedUserDTO createdUserDTO){
        User user = this.userService.createdUser(createdUserDTO);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    @GetMapping("/{user-id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "user-id") int userId) {
        UserResponseDTO UserResponseDTO = userService.getUserById(userId);
        return new ResponseEntity<>(UserResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createdUser(@RequestBody CreatedUserDTO requestDTO) {
        User user = userService.createdUser(requestDTO);
        return new ResponseEntity(user, HttpStatus.OK);
    }


    @PutMapping("/{user-id}")
    public ResponseEntity updateUser(@PathVariable(value = "user-id") int userId,
                                     @RequestBody UpdateUserDTO updateUserRequestDTO) {
        UserResponseDTO response = userService.updateUser(updateUserRequestDTO, userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable(value = "user-id") int userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

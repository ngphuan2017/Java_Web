package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedUserDTO;
import JavaWeb.SpringBoot.dto.request.UpdateUserDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.dto.response.UserResponseDTO;
import JavaWeb.SpringBoot.entity.User;
import JavaWeb.SpringBoot.mapper.AddressMapper;
import JavaWeb.SpringBoot.mapper.UserMapper;
import JavaWeb.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<User> getAllUser() {
        Iterable<User> userIterable = this.userRepository.findAll();
        return (List)userIterable;
    }
    @Override
    @Transactional
    public User createdUser(CreatedUserDTO createdUserDTO) {
        User user = new User();
        user.setUsername(createdUserDTO.getUsername());
        user.setPassword(createdUserDTO.getPassword());
        user.setPhone(createdUserDTO.getPhone());
        user.setGender(createdUserDTO.getGender());
        user.setEmail(createdUserDTO.getEmail());
        user.setImgLink(createdUserDTO.getImgLink());
        this.userRepository.save(user);
        return user;
    }
    @Override
    public PageResponseDTO getUserPaging() {
        Pageable pageable = Pageable.ofSize(2).withPage(0);
        Page<User> userPage = userRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Error"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(userPage.getNumber());
        pageResponseDTO.setSize(userPage.getSize());
        pageResponseDTO.setTotalPages(userPage.getTotalPages());
        pageResponseDTO.setTotalRecord(userPage.getTotalElements());
        List<UserResponseDTO> userResponseDTOS = userMapper.convertEntityToResponseDtos(userPage.getContent());
        pageResponseDTO.setData(userResponseDTOS);

        return pageResponseDTO;
    }
    @Override
    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        UserResponseDTO userResponseDTOS = userMapper.convertEntityToResponseDto(user);
        return userResponseDTOS;
    }
    @Override
    @Transactional
    public UserResponseDTO updateUser(UpdateUserDTO requestDTO, Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setPhone(requestDTO.getPhone());
        user.setGender(requestDTO.getGender());
        user.setEmail(requestDTO.getEmail());
        user.setImgLink(requestDTO.getImgLink());
        userRepository.save(user);
        UserResponseDTO userResponseDTO = userMapper.convertEntityToResponseDto(user);
        return userResponseDTO;
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        userRepository.delete(user);
    }
}

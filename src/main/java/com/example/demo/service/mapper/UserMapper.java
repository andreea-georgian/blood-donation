package com.example.demo.service.mapper;

import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreateDTO dto) {
        User user = new User();
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.uuid = user.getId();
        dto.email = user.getEmail();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        return dto;
    }
}

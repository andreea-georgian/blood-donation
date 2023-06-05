package com.example.demo.service;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService {

    User findById(Integer id);
    DonorDTO registerUser(DonorCreateDTO dto);

    UserDTO loginUser(UserLoginDTO dto);
    void deleteUser(Integer id);
}

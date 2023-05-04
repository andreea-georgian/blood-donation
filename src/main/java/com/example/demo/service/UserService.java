package com.example.demo.service;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;

public interface UserService {

    DonorDTO registerUser(DonorCreateDTO dto);

    UserDTO loginUser(UserCreateDTO dto);
    void deleteUser(Integer id);
}

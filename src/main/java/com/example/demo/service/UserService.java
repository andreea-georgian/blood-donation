package com.example.demo.service;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserDTO;

public interface UserService {

    DonorDTO registerUser(DonorCreateDTO dto);

    UserDTO loginUser(UserLoginDTO dto);
    void deleteUser(Integer id);
}

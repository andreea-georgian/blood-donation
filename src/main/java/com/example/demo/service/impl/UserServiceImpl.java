package com.example.demo.service.impl;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Donor;
import com.example.demo.entity.User;
import com.example.demo.repository.DonorRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.mapper.DonorMapper;
import com.example.demo.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DonorRepository donorRepository;
    private final UserMapper userMapper;
    private final DonorMapper donorMapper;

    public UserServiceImpl(UserRepository userRepository, DonorRepository donorRepository, UserMapper userMapper, DonorMapper donorMapper) {
        this.userRepository = userRepository;
        this.donorRepository = donorRepository;
        this.userMapper = userMapper;
        this.donorMapper = donorMapper;
    }

    @Override
    public DonorDTO registerUser(DonorCreateDTO dto) {
        if (userRepository.findByEmail(dto.email).isEmpty()) {
            Donor savedDonor = donorRepository.save(donorMapper.toDonor(dto));
            return donorMapper.toDTO(savedDonor);
        }
        else
            return null;
    }

    @Override
    public UserDTO loginUser(UserLoginDTO dto) {
        Optional<User> user = userRepository.findByEmailAndPassword(dto.email, dto.password);
        if (user.isEmpty())
            return null;
        else
            return userMapper.toDTO(user.get());
    }

    @Override
    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            userRepository.delete(user.get());
    }
}

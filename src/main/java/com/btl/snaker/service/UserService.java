package com.btl.snaker.service;

import com.btl.snaker.dto.PageUserDTO;
import com.btl.snaker.dto.UserDTO;
import com.btl.snaker.entity.Role;
import com.btl.snaker.entity.User;
import com.btl.snaker.mapper.UserMapper;
import com.btl.snaker.payload.ResponseData;
import com.btl.snaker.repository.UserRepository;
import com.btl.snaker.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.toDTOList(users);
    }

    @Override
    public ResponseData getAllUserByAdmin(int page) {
        ResponseData responseData = new ResponseData();
        Pageable pageable = PageRequest.of(page, 10);
        Page<User> userPage = userRepository.findAll(pageable);
        PageUserDTO pageUserDTO = new PageUserDTO(UserMapper.toDTOList(userPage.getContent()), userPage.getTotalPages(), userPage.getTotalElements());
        responseData.setData(pageUserDTO);
        responseData.setSuccess(true);
        return responseData;
    }

    @Override
    public ResponseData getUserById(long id) {
        ResponseData responseData = new ResponseData();
        User user = userRepository.findById(id);
        if(user == null) {
            responseData.setSuccess(false);
            responseData.setDescription("User not found");
            return responseData;
        }
        responseData.setSuccess(true);
        responseData.setData(UserMapper.toUserDTO(user));
        return responseData;
    }

    @Override
    public ResponseData getUserByEmail(String email) {
        ResponseData responseData = new ResponseData();
        User user = userRepository.findByEmail(email);
        if(user == null) {
            responseData.setSuccess(false);
            responseData.setDescription("User not found");
            return responseData;
        }
        responseData.setSuccess(true);
        responseData.setData(UserMapper.toUserDTO(user));
        return responseData;
    }

    @Override
    public Boolean deleteUser(long id) {
        User user = userRepository.findById(id);
        if(user != null) {
            user.setActive(0);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean assignUser(long id) {
        User user = userRepository.findById(id);
        if(user != null) {
            Role role = new Role();
            role.setId((long) 1);
            user.setRole(role);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public ResponseData changePassword(long id, String oldPassword, String newPassword) {
        ResponseData responseData = new ResponseData();
        User user = userRepository.findById(id);
        if(user == null) {
            responseData.setSuccess(false);
            responseData.setDescription("User not found");
            return responseData;
        }
        if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
            responseData.setSuccess(false);
            responseData.setDescription("Wrong password");
            return responseData;
        }
        if(!passwordEncoder.matches(newPassword, user.getPassword())) {
            responseData.setSuccess(false);
            responseData.setDescription("Exist password");
            return responseData;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        responseData.setSuccess(true);
        responseData.setDescription("Successful");
        return responseData;
    }

    @Override
    public ResponseData updateInformation(long id, String phone, String address) {
        ResponseData responseData = new ResponseData();
        User user = userRepository.findById(id);
        if(user == null) {
            responseData.setSuccess(false);
            responseData.setDescription("User not found");
            return responseData;
        }
        user.setPhone(phone);
        user.setAddress(address);
        userRepository.save(user);
        responseData.setSuccess(true);
        responseData.setDescription("Successful");
        return responseData;
    }
}

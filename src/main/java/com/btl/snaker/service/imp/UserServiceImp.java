package com.btl.snaker.service.imp;

import com.btl.snaker.dto.UserDTO;
import com.btl.snaker.payload.ResponseData;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getAllUsers();
    ResponseData getAllUserByAdmin(int page);
    ResponseData getUserById(long id);
    ResponseData getUserByEmail(String email);
    Boolean deleteUser(long id);
    Boolean assignUser(long id);
    ResponseData changePassword(long id, String oldPassword, String newPassword);
    ResponseData updateInformation(long id, String phone, String address);
}

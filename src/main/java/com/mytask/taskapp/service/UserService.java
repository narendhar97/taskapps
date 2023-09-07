package com.mytask.taskapp.service;

import com.mytask.taskapp.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);    //save
    UserDto findUserById(Long id);          //get by id
    UserDto updateUserById(UserDto userDto ,Long id); //update
    void deleteUserById(Long id);           //delete user
    List<UserDto> findAllUsers();    // list users
}

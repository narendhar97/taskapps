package com.mytask.taskapp.service;

import com.mytask.taskapp.Constants.UserConstants;
import com.mytask.taskapp.Exception.UserIdNotFoundException;
import com.mytask.taskapp.entity.User;
import com.mytask.taskapp.payload.UserDto;
import com.mytask.taskapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        //connvert dto to  entity  and save entity
        User user = modelMapper.map(userDto, User.class);
        User user1 = userRepository.save(user);
        //convert entity to dto and return dto
        UserDto userdto = modelMapper.map(user1, UserDto.class);
        return userdto;

    }

    @Override
    public UserDto findUserById(Long id) {
         User user = userRepository.findById(id).orElseThrow(() ->new UserIdNotFoundException(UserConstants.USER_NOT_FOUND +id));
            //convert user to dto
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }

         @Override
         public UserDto updateUserById(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->new UserIdNotFoundException(UserConstants.USER_NOT_FOUND +id));
            user.setFirstName((userDto.getFirstName()));
            user.setLastName((userDto.getLastName()));
            user.setEmail((userDto.getEmail()));
            user.setPassword((userDto.getPassword()));
            user.setMobileNumber((userDto.getMobileNumber()));
         //convert user to dto
            UserDto dto = modelMapper.map(user, UserDto.class);
         return dto;

    }

         @Override
         public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundException(UserConstants.USER_DELETED + id));
        userRepository.deleteById(id);

    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDtoList = users.stream().map((any) -> modelMapper.map(users, UserDto.class)).collect(Collectors.toList());
        return usersDtoList;
    }
}


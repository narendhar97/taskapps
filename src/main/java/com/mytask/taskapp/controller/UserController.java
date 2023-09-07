package com.mytask.taskapp.controller;

import com.mytask.taskapp.payload.HttpResponse;
import com.mytask.taskapp.payload.UserDto;
import com.mytask.taskapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mytask.taskapp.Constants.UserConstants.USER_CREATED;
import static com.mytask.taskapp.Constants.UserConstants.USER_DELETED;

@RestController
@RequestMapping("/api/users")
public class UserController {


    //service object is created
    @Autowired
    private UserService userService;

    //https://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<HttpResponse> getUser(@RequestBody UserDto userDto) {
        UserDto creteUserDto = userService.saveUser(userDto);
        HttpResponse response = httpResponse();
        response.setData(creteUserDto);
        //dout created i think method calling in hhtpresponse  showing  in postman of
        response.setStatus(HttpStatus.CREATED.getReasonPhrase());
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage(USER_CREATED);

        return new ResponseEntity<HttpResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUseById(@PathVariable Long id) {
        UserDto userDto = userService.findUserById(id);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> usersList = userService.findAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userDto, @PathVariable Long id) {
        UserDto updateUser = userService.updateUserById(userDto, id);
        return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("d/{id}")
    public ResponseEntity<Map<String, Long>> deleteUserId(@PathVariable Long id) {
        Map<String, Long> map = new HashMap<>();
        map.put(USER_DELETED, id);
        userService.deleteUserById(id);
        return new ResponseEntity<Map<String, Long>>(map, HttpStatus.OK);

    }


    public HttpResponse httpResponse() {
        return new HttpResponse();

    }
}


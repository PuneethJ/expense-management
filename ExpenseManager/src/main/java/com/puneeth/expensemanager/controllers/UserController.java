package com.puneeth.expensemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.puneeth.expensemanager.Dto.UserDto;
import com.puneeth.expensemanager.entities.User;
import com.puneeth.expensemanager.services.UserService;


@RestController
public class UserController {
    
    @Autowired
    public UserService userService;

   

    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PutMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateById(@RequestBody UserDto userDto,@PathVariable Long id){
        return userService.updateById(userDto,id);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }
}

package com.puneeth.expensemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.puneeth.expensemanager.Dto.UserDto;
import com.puneeth.expensemanager.entities.User;
import com.puneeth.expensemanager.exceptions.ItemAlreadyExistsException;
import com.puneeth.expensemanager.exceptions.ResourceNotFoundException;
import com.puneeth.expensemanager.repos.UserRepo;

@Service
public class UserService {
    @Autowired
    public UserRepo userRepo;
    @Autowired
    public PasswordEncoder bcryptEncoder;

    public User createUser(UserDto userDto){
        if(userRepo.existsByEmail(userDto.getEmail())){
            throw new ItemAlreadyExistsException("User already exists with mail: "+userDto.getEmail());
        }
        User user= new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        
        return userRepo.save(user);
    }

    public User findById(Long id){
        User user= userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+ id));
        return user;
    }

    public User updateById(UserDto userDto, Long id) {
        User user= userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+ id));
        user.setName(userDto.getName()!=null?userDto.getName():user.getName());
        user.setEmail(userDto.getEmail()!=null?userDto.getEmail():user.getEmail());
        user.setPassword(userDto.getPassword()!=null?bcryptEncoder.encode(userDto.getPassword()):user.getPassword());
        User updatedUser=userRepo.save(user);
        return updatedUser;
    }

    public void deleteById(Long id){
        User user= userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+ id));
        userRepo.delete(user);
    }

    public User getLoggedInUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        User user= userRepo.findByEmail(email);
        return user;
    }
    
}

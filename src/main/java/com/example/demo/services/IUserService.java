package com.example.demo.services;

import com.example.demo.models.UserModel;
import org.apache.catalina.User;

import java.util.List;

public interface IUserService {

    List<UserModel> getAllUsers();
    UserModel addUser(UserModel userModel);

}

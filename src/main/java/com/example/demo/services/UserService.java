package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<User> users = userRepository.findAll();
        Type listType = new TypeToken<List<UserModel>>(){}.getType();
        List<UserModel> userModels = modelMapper.map(users, listType);
        return userModels;
    }

    @Override
    public UserModel addUser(UserModel userModel) {
        User userToAdd = modelMapper.map(userModel, User.class);
        return modelMapper.map(userRepository.save(userToAdd), UserModel.class);
    }
}

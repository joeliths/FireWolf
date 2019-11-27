package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.UserRole;
import com.example.demo.exceptions.UserRoleTypeNotFoundException;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserRegisterModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserModel> getAllUsers() {
        List<User> users = userRepository.findAll();
        Type listType = new TypeToken<List<UserModel>>(){}.getType();
        List<UserModel> userModels = modelMapper.map(users, listType);
        return userModels;
    }

    public UserModel addUser(UserRegisterModel userModel) {
        User userToAdd = modelMapper.map(userModel, User.class);
        Set<UserRole> roles = userModel.getRoles().stream()
                                .map(r -> getRoleByName(r))
                                .collect(Collectors.toSet());
        userToAdd.setRoles(roles);
        UserModel userToReturn = modelMapper.map(userRepository.save(userToAdd), UserModel.class);
        return userToReturn;
    }

    public UserRole getRoleByName(String roleType){
        Optional<UserRole> role = userRoleRepository.getUserRoleByRole(roleType);
        return role.orElseThrow(() -> new UserRoleTypeNotFoundException("Role with type "+roleType+" not found"));
    }
}

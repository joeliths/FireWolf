package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.UserRole;
import com.example.demo.exceptions.customExceptions.UserNotFoundException;
import com.example.demo.exceptions.customExceptions.UserRoleTypeNotFoundException;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserRegisterModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserModel> getAllUsers() {
        List<User> users = userRepository.findAll();
        Type listType = new TypeToken<List<UserModel>>(){}.getType();
        List<UserModel> userModels = modelMapper.map(users, listType);
        return userModels;
    }

    public UserModel addUser(UserRegisterModel userModel) {
        User userToAdd = modelMapper.map(userModel, User.class);
        String encryptedPass = passwordEncoder.encode(userModel.getPassword());
        Set<UserRole> roles = userModel.getRoles().stream()
                                .map(r -> getRoleByName(r))
                                .collect(Collectors.toSet());
        userToAdd.setRoles(roles);
        userToAdd.setPassword(encryptedPass);
        UserModel userToReturn = modelMapper.map(userRepository.save(userToAdd), UserModel.class);
        return userToReturn;
    }

    public UserRole getRoleByName(String roleType){
        Optional<UserRole> role = userRoleRepository.getUserRoleByRole(roleType);
        return role.orElseThrow(() -> new UserRoleTypeNotFoundException("Role with type "+roleType+" not found"));
    }

    public User getUserByUserName(String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        return user.orElseThrow(() -> new UserNotFoundException("User with username "+userName+" not found"));
    }

    //TODO:Write this method
    public boolean deleteUser(UserModel userModel) {
        return false;
    }
}

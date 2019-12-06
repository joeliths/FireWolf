package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.entities.UserRole;
import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.exceptions.customExceptions.UserRoleTypeNotFoundException;
import com.example.demo.models.user.UserModel;
import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.models.user.UserResponseModel;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    public UserResponseModel findUserByUuid(String uuid) {
        Optional<User> foundUser = userRepository.findByUuid(uuid);
        if(foundUser.isEmpty()) {
            throw new NotFoundException("No user with uuid '" + uuid + "' was found."); //Todo: handle exception
        }
        return toModel(foundUser.get());
    }

    public User getUserByUserName(String userName){
        Optional<User> foundUser = userRepository.findByUserName(userName);
        return foundUser.orElseThrow(() -> new UsernameNotFoundException("User with that username not found"));
    }

    public UserResponseModel registerUser(UserRegisterModel userModel) {
        User userToAdd = toEntity(userModel);
        userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));
        Customer customerToAdd = new Customer();
        customerToAdd.setUser(userToAdd);
        customerRepository.save(customerToAdd);
        return toModel(userToAdd);
        //Todo: user validation + exceptions
    }

    public UserRole getRoleByName(String roleType){
        Optional<UserRole> role = userRoleRepository.getUserRoleByRole(roleType);
        return role.orElseThrow(() -> new UserRoleTypeNotFoundException("Role with type "+roleType+" not found"));
    }

    @Transactional
    public int deleteUserByUUID(String uuid) {
        return userRepository.deleteByUuid(uuid);
    }






    //TODO: replace with modelmapper!
    public User toEntity(UserRegisterModel userModel) {
        User userToAdd = new User(userModel.getFullName(), userModel.getUserName(), userModel.getPassword());
        Set<UserRole> roles = userModel.getRoles().stream()
                .map(r -> getRoleByName(r))
                .collect(Collectors.toSet());
        userToAdd.setRoles(roles);
        return userToAdd;
    }
    //TODO: replace with modelmapper!
    public UserResponseModel toModel(User userEntity) {
        return new UserResponseModel(
                userEntity.getFullName(), userEntity.getUserName(), userEntity.getUuid().toString()
        );
    }
}

package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.entities.UserRole;
import com.example.demo.exceptions.customExceptions.UserRoleTypeNotFoundException;
import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.models.user.UserResponseModel;
import com.example.demo.models.view.ViewTest;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.validation.ValidationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.ws.rs.NotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       CustomerRepository customerRepository, PasswordEncoder passwordEncoder,
                       ValidationService validationService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    public List<UserResponseModel> getAllUsers() {
        return userRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }

    public UserResponseModel getUserByUuid(String uuid) {
        Optional<User> foundUser = userRepository.findByUuid(uuid);
        if(foundUser.isEmpty()) {
            throw new EntityNotFoundException("No user with uuid '" + uuid + "' was found.");
        }
        return toModel(foundUser.get());
    }

    public UserResponseModel getUserByUserName(String userName){
        Optional<User> foundUser = userRepository.findByUserName(userName);
        if(foundUser.isEmpty()) {
            throw new EntityNotFoundException("No user with username '" + userName + "' was found.");
        }
        return toModel(foundUser.get());
    }

    public User getUserByUserNameReturnEntity(String userName) {
        Optional<User> foundUser = userRepository.findByUserName(userName);
        if(foundUser.isEmpty()) {
            throw new EntityNotFoundException("No user with username '" + userName + "' was found.");
        }
        return foundUser.get();
    }

    public UserResponseModel registerUser(UserRegisterModel userModel) {
        boolean modelContainsNullFields = validationService.checkIfAnyFieldsAreNull(
                userModel.getPassword(), userModel.getFullName(), userModel.getUserName());

        if(modelContainsNullFields) {
            throw new ValidationException("All fields must be included.");
        }
        if(userRepository.findByUserName(userModel.getUserName()).isPresent()){
            throw new ValidationException("Username '" + userModel.getUserName() + "' already exists.");
        }

        User userToAdd = toEntity(userModel);
        userToAdd.setPassword(passwordEncoder.encode(userToAdd.getPassword()));
        Customer customerToAdd = new Customer();
        customerToAdd.setUser(userToAdd);
        customerRepository.save(customerToAdd);
        return toModel(userToAdd);
    }

    @Transactional
    public void deleteUserByUUID(String uuid) {
        userRepository.deleteByUuid(uuid);
    }

    public UserResponseModel patchUser(String userUuid, UserRegisterModel updatedUser) {
        if(userRepository.findByUserName(updatedUser.getUserName()).isPresent()) {
            throw new ValidationException("Username '" + updatedUser.getUserName() + "' already exists.");
        }

        User foundUser = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new EntityNotFoundException("No user with uuid '" + userUuid + "' was found."));

        if(updatedUser.getUserName() != null) {
            foundUser.setUserName(updatedUser.getUserName());
        }
        if(updatedUser.getPassword() != null) {
            foundUser.setPassword(updatedUser.getPassword());
        }
        if(updatedUser.getFullName() != null) {
            foundUser.setFullName(updatedUser.getFullName());
        }

        return toModel(userRepository.save(foundUser));
    }

    private UserRole getRoleByName(String roleType){
        Optional<UserRole> role = userRoleRepository.getUserRoleByRole(roleType);
        return role.orElseThrow(() -> new UserRoleTypeNotFoundException("Role with type "+roleType+" not found"));
    }




    //TODO: replace with modelmapper!
    public User toEntity(UserRegisterModel userModel) {
        User userToAdd = new User(userModel.getFullName(), userModel.getUserName(), userModel.getPassword());
        Set<UserRole> roles = new HashSet<>();
//                userModel.getRoles().stream()
//                .map(r -> getRoleByName(r))
//                .collect(Collectors.toSet());
        userToAdd.setRoles(roles);
        return userToAdd;
    }
    //TODO: replace with modelmapper!
    public UserResponseModel toModel(User userEntity) {
        return new UserResponseModel(
                userEntity.getFullName(), userEntity.getUserName(), userEntity.getUuid().toString()
        );
    }


    public ViewTest findById(int i) {
        return userRepository.getById(1);
    }


}

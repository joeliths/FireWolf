package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.entities.UserRole;
import com.example.demo.models.user.RoleModel;
import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.models.user.UserResponseModel;
import com.example.demo.models.user.UserWithRolesResponseModel;
import com.example.demo.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final Convert modelConverter;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, CustomerRepository customerRepository,
                       PasswordEncoder passwordEncoder, Convert modelConverter,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelConverter = modelConverter;
        this.userRoleRepository = userRoleRepository;
    }

    public void registerUserAndCustomer(UserRegisterModel user){
        boolean areUserFieldsInvalid = Stream.of(user.getUserName(), user.getPassword(), user.getFullName())
                .anyMatch(string -> string == null || string.isBlank());
        if(areUserFieldsInvalid) {
            throw new ValidationException("Missing fields for user.");
        }
        else if (isUserNameTaken(user.getUserName())) {
            throw new ValidationException("Username is already taken.");
        }

        Customer customerToAdd = new Customer();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userEntity = modelConverter.lowAccessConverter(user, User.class);
        UserRole customerRole = userRoleRepository.getUserRoleByRole("ROLE_CUSTOMER").get();
        UserRole userRole = userRoleRepository.getUserRoleByRole("ROLE_USER").get();
        userEntity.setRoles(new HashSet<UserRole>(Arrays.asList(customerRole, userRole)));
        customerToAdd.setUser(userEntity);
        customerRepository.save(customerToAdd);
    }

    public void deleteUser(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public void patchUser(String userName, UserRegisterModel newUserFields) {

        User userToUpdate = userRepository.getByUserName(userName);

        if(newUserFields.getUserName() != null) {
            if(isUserNameTaken(newUserFields.getUserName())) {
                throw new ValidationException("Username is taken.");
            }
            userToUpdate.setUserName(newUserFields.getUserName());
        }
        if(newUserFields.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode(newUserFields.getPassword()));
        }
        if(newUserFields.getFullName() != null) {
            userToUpdate.setFullName(newUserFields.getFullName());
        }

        userRepository.patchUser(userToUpdate);
    }

    private boolean isUserNameTaken(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    public boolean checkIfEntityBelongsToUser(String username, long entityUserId){
        User foundUser = userRepository.findByUserName(username)
                .orElseThrow(EntityNotFoundException::new);
        long id = foundUser.getId();
        return id == entityUserId;
    }

    public UserResponseModel getUserByUserName(String userName){
        User user = userRepository.findByUserName(userName).get();
        return modelConverter.lowAccessConverter(user, UserResponseModel.class);
    }

    public UserWithRolesResponseModel getUserByUserWithRolesByUserName(String userName){
        User user = userRepository.findByUserName(userName).get();
        Set<RoleModel> userRoles = user.getRoles().stream().map(r -> new RoleModel(r.getRole())).collect(Collectors.toSet());
        return new UserWithRolesResponseModel(user.getFullName(), user.getUserName(), user.getUuid().toString(), userRoles);
    }

}

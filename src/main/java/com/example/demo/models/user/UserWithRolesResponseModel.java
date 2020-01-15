package com.example.demo.models.user;

import java.io.Serializable;
import java.util.Set;

public class UserWithRolesResponseModel extends UserResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<RoleModel> roles;

    public UserWithRolesResponseModel() {
    }

    public UserWithRolesResponseModel(String fullName, String userName, String uuid, Set<RoleModel> userRoles) {
        super(fullName, userName, uuid);
        this.roles = userRoles;
    }

    public Set<RoleModel> getUserRoles() {
        return roles;
    }

    public void setUserRoles(Set<RoleModel> userRoles) {
        this.roles = userRoles;
    }
}

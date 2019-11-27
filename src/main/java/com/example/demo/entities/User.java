package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Embedded
    private MyUUID uuid = new MyUUID();

    public MyUUID getUuid() {
        return uuid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String fullName;
    @Column(nullable = false, length = 50, unique = true)
    private String userName;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(name = "USER_ROLE")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles;

    
    public User() {
    }

    public User(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}

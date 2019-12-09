package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable, MyEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private MyUUID uuid = new MyUUID();

    @Column(name ="user_name",
            nullable = false, unique = true)
    private String userName;
    @Column(name = "full_name",
            nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;

    @Column(name = "USER_ROLE")
    @ManyToMany(fetch = FetchType.EAGER)
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

    public MyUUID getUuid() {
        return uuid;
    }
}

package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.dao.RoleDao;
import com.blog.dao.UserDao;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.exception.BlogException;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Role for Admin");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("Shubham");
        adminUser.setUserPassword(getEncodedPassword("Shubham"));
        adminUser.setUserFirstName("Shubham");
        adminUser.setUserLastName("Shubham");
        adminUser.setEmail("abc@def.com");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
    }

    public User registerNewUser(User user) {
    	
    	if (userDao.existsByUserName(user.getUserName())) {
            throw new IllegalArgumentException("Username is already taken");
        }
    	
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
    
    public User getuser(String name) {
    	return this.userDao.findById(name).orElseThrow(()-> new BlogException("User Not Found"));
    }
}

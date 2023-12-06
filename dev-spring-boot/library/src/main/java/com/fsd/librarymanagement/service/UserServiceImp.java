package com.fsd.librarymanagement.service;

import com.fsd.librarymanagement.DAO.RoleRepository;
import com.fsd.librarymanagement.DAO.UserRepository;
import com.fsd.librarymanagement.entity.Role;
import com.fsd.librarymanagement.entity.User;
import com.fsd.librarymanagement.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void save(User user, String roleName) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new CustomException("Username already exists."); // Throw exception if username exists
        }
        Role role = roleRepository.findByName(roleName); // Find the role by name
        Role employee = roleRepository.findByName("ROLE_EMPLOYEE"); // Find the 'ROLE_EMPLOYEE' role
        if (role != null) {
            user.getRoles().add(role); // Add the found role to the user
            user.getRoles().add(employee); // Also add 'ROLE_EMPLOYEE' to the user
        }
        userRepository.save(user); // Save the user to the repository
    }

}

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
            throw new CustomException("Username already exists.");
        }
        Role role = roleRepository.findByName(roleName); // 查找角色
        Role employee = roleRepository.findByName("ROLE_EMPLOYEE");
        if (role != null) {
            user.getRoles().add(role); // 分配角色
            user.getRoles().add(employee);
        }
        userRepository.save(user);
    }

}

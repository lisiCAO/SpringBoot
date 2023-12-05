package com.fsd.librarymanagement.service;

import com.fsd.librarymanagement.entity.User;

public interface UserService {
    void save(User user, String roleName);
}

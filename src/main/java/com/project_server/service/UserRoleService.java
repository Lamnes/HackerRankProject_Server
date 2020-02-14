package com.project_server.service;

import com.project_server.dao.UserRoleDao;
import com.project_server.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleService {
    private UserRoleDao repository;

    @Autowired
    private UserRoleService(UserRoleDao repository) {
        this.repository = repository;
    }

    public List<UserRole> getAllUserRoles() {
        return repository.findAll();
    }

    public UserRole getUserRoleById(long id) {
        return repository.getOne(id);
    }
}
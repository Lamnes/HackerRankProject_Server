package com.project_server.service;

import com.project_server.dao.UserDao;
import com.project_server.exception.ServiceException;
import com.project_server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private UserDao repository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService(UserDao repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void removeUser(long id) {
        User foundUser = repository.getOne(id);
        if (foundUser == null) {
            throw new ServiceException("User to remove NOT found");
        }

        repository.delete(foundUser);
    }

    public User getUserById(long id) {
        return repository.getOne(id);
    }

    public User getUserByName(String name) {
        User filter = new User();
        filter.setName(name);

        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Example<User> example = Example.of(filter, caseInsensitiveExampleMatcher);

        List<User> found = repository.findAll(example);
        if (found.isEmpty()) {
            return null;
        }

        return found.get(0);
    }

    public void updateUser(User user) {
        repository.save(user);
    }

    public void createUser(User user) {
        repository.save(user);
    }
}

package com.project_server.controller;

import com.project_server.model.User;
import com.project_server.model.UserRole;
import com.project_server.service.UserRoleService;
import com.project_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/restUserSave")

    public User saveUser(@RequestBody User user) {
        final String EMPTY__PASSWORD_VALUE = "";
        String encryptedPassword;

        if (user.getPassword().equals(EMPTY__PASSWORD_VALUE)) {
            User currentUserData = userService.getUserById(user.getId());
            encryptedPassword = currentUserData.getPassword();
        } else {
            encryptedPassword = passwordEncoder.encode(user.getPassword());
        }

        user.setPassword(encryptedPassword);

        if (user.getId() == 0) {
            userService.createUser(user);
        } else {
            userService.updateUser(user);
        }

        return user;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/restUserRemove")
    public void removeUser(HttpServletRequest req) {
        int userIdToRemove = Integer.parseInt(req.getParameter("id"));
        userService.removeUser(userIdToRemove);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/restUserList")

    public List<User> showUserList() {
        return userService.getAllUsers();
    }

    @GetMapping("/restUser")

    public User showUserInfo(@RequestParam(name = "id", defaultValue = "0") long id, @RequestParam(name = "name", defaultValue = "") String name) {
        if (id != 0) {
            return userService.getUserById(id);
        }

        return userService.getUserByName(name);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/restUserRoles")

    public List<UserRole> showUserRoles() {
        return userRoleService.getAllUserRoles();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/restUserRole")

    public UserRole showUserRoleById(@RequestParam long id) {
        return userRoleService.getUserRoleById(id);
    }
}

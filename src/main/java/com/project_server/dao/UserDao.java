package com.project_server.dao;

import com.project_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserDao extends JpaRepository<User, Long>, QueryByExampleExecutor<User> {
}

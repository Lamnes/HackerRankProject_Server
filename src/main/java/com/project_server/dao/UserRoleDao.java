package com.project_server.dao;

import com.project_server.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
}

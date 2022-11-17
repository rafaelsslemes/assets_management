package com.spexcode.asset_management.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByLogin (String login);
}

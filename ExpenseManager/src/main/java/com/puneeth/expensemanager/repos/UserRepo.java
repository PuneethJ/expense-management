package com.puneeth.expensemanager.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puneeth.expensemanager.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    boolean existsByEmail(String email);

    User findByEmail(String username);
}

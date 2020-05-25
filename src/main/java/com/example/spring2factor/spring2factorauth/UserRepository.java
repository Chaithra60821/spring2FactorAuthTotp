package com.example.spring2factor.spring2factorauth;

import com.example.spring2factor.spring2factorauth.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
     Optional<User> findByUsername(String username);
}

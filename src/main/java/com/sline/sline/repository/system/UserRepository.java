package com.sline.sline.repository.system;

import com.sline.sline.entity.system.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(String uuid);
    User findByEmail(String email);
    List<User> findAllByEnabled(boolean enabled);
    void deleteByUuid(String uuid);
}

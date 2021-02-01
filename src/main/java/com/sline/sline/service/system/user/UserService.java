package com.sline.sline.service.system.user;

import com.sline.sline.entity.system.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUuid(String uuid);
    User findByEmail(String email);
    List<User> findAllByEnabled(boolean enabled);
    void save(User user);
    void delete(User user);
    void delete(String uuid);
}

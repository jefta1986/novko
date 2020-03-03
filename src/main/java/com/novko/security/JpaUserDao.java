package com.novko.security;

import java.util.List;

public interface JpaUserDao {

    void add(User user);
    void update(User user);
    void delete(User user);
    List<User> getUsers();
    User findByUsername(String userName);
    User getById(Long userId);

}

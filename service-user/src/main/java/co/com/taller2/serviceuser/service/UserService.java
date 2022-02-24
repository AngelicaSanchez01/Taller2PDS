package co.com.taller2.serviceuser.service;

import co.com.taller2.serviceuser.entities.User;

import java.util.List;

public interface UserService {

    void save(User user);
    void delete(User user);
    List<User> findAll();
    User findById(Long id);
}

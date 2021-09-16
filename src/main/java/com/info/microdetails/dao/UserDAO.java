package com.info.microdetails.dao;

import com.info.microdetails.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    public List<User> findAll();
    public Optional<User> findUserById(long id);
    public User save(User user);

}

package com.info.microdetails.daoImpl;

import com.info.microdetails.dao.UserDAO;
import com.info.microdetails.model.User;
import com.info.microdetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserDAO {

    private final UserRepository userRepository;

    @Autowired
    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User>findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }


    @Override
    public User save(User _user) {
        return userRepository.save(_user);
    }


}

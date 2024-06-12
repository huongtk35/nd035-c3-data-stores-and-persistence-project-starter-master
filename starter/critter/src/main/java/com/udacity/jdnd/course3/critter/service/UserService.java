/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:42:29
 * @modify date 2024-06-12 21:42:29
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.User;
import com.udacity.jdnd.course3.critter.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
       return this.userRepository.save(user);
    }

    public List<User> getAllCustomers() {
        return this.userRepository.findAll();
    }
}

package ru.bogatov.AutoRent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bogatov.AutoRent.Dao.UsersRepo;
import ru.bogatov.AutoRent.Entities.Role;
import ru.bogatov.AutoRent.Entities.User;

import java.util.Collections;

public class UserService implements UserServiceable{
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserByMail(String mail){
        return usersRepo.findByUsername(mail);
    }

    public User findByUsername(String username){
        return usersRepo.findByUsername(username);
    }

    public void save(User user){
        usersRepo.save(user);
    }

    public Boolean registerUser(User user){
        User userBD = this.findByUsername(user.getUsername());

        if(userBD != null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(user);
        return true;
    }
}

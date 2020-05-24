package ru.bogatov.AutoRent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogatov.AutoRent.Dao.UsersRepo;
import ru.bogatov.AutoRent.Entities.User;

public class UserService implements UserServiceable{
    @Autowired
    UsersRepo usersRepo;

    public User getUserByMail(String mail){
        return usersRepo.findByUsername(mail);
    }

    public User findByUsername(String username){
        return usersRepo.findByUsername(username);
    }

    public void save(User user){
        usersRepo.save(user);
    }
}

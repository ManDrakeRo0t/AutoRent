package ru.bogatov.AutoRent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogatov.AutoRent.Dao.UsersRepo;
import ru.bogatov.AutoRent.Entities.User;
@Service
public class UserService {
    @Autowired
    UsersRepo usersRepo;

    public User getUserByMail(String mail){
        return usersRepo.findByUsername(mail);
    }
}

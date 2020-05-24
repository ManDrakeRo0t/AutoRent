package ru.bogatov.AutoRent.Services;

import ru.bogatov.AutoRent.Entities.User;

public interface UserServiceable {
    public User getUserByMail(String mail);
    void save(User user);
    User findByUsername(String username);
}

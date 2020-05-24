package ru.bogatov.AutoRent.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogatov.AutoRent.Entities.User;

@Repository
public interface UsersRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}

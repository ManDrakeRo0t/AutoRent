package ru.bogatov.AutoRent.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogatov.AutoRent.Entities.CarClass;
@Repository
public interface CarClassesRepo extends JpaRepository<CarClass,Long> {
    public CarClass getByName(String name);
}

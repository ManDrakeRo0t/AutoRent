package ru.bogatov.AutoRent.Dao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bogatov.AutoRent.Entities.Car;

import java.util.Optional;

@Repository
public interface CarsRepo extends JpaRepository<Car,Long> {

    public Iterable<Car> findByPriceLessThan(Integer price);

    public Car findByModel(String model);

    public Car findById(Integer id);
}

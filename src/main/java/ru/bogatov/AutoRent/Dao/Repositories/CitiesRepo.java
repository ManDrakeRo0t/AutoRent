package ru.bogatov.AutoRent.Dao.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bogatov.AutoRent.Entities.City;

import java.util.Optional;

@Repository
public interface CitiesRepo extends CrudRepository<City,Long> {
    public Optional<City> findCityByName(String name);
}

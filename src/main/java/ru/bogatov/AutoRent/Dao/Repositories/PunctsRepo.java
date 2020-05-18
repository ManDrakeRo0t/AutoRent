package ru.bogatov.AutoRent.Dao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogatov.AutoRent.Entities.City;
import ru.bogatov.AutoRent.Entities.Punct;
@Repository
public interface PunctsRepo extends JpaRepository<Punct,Long> {
    public Iterable<Punct> getPunctsByCity(City city);

    public Punct findByAddress(String address);
}

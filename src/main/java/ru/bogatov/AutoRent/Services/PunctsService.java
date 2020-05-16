package ru.bogatov.AutoRent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogatov.AutoRent.Dao.Repositories.CitiesRepo;
import ru.bogatov.AutoRent.Dao.Repositories.PunctsRepo;
import ru.bogatov.AutoRent.Entities.City;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PunctsService {
    @Autowired
    CitiesRepo citiesRepo;
    @Autowired
    PunctsRepo punctsRepo;

    public List<String> getCitiesForCar(Integer id){
        Iterable<City> cities = citiesRepo.findAll();
        Iterator<City> cityIterator = cities.iterator();
        List<String> res = new ArrayList<>();

        while(cityIterator.hasNext()){
            City city = cityIterator.next();
            String[] carsId = city.getAvailableCars().split("-");

            for(String s : carsId) if(s.equals(id.toString())) res.add(city.getName());
        }

        return res;
    }
}

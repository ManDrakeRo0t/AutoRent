package ru.bogatov.AutoRent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogatov.AutoRent.Dao.Repositories.CarsRepo;
import ru.bogatov.AutoRent.Dao.Repositories.CitiesRepo;
import ru.bogatov.AutoRent.Dao.Repositories.PunctsRepo;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.City;
import ru.bogatov.AutoRent.Entities.Punct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PunctsService {
    @Autowired
    CitiesRepo citiesRepo;
    @Autowired
    PunctsRepo punctsRepo;
    @Autowired
    CarsRepo carsRepo;

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

    public Iterable<City> getAllCities(){
        return citiesRepo.findAll();
    }

    public List<String> getPunctsForCity(String name){
        City city = citiesRepo.findCityByName(name).get(); //todo проверку
        List<String> puncts = new ArrayList<>();
        punctsRepo.getPunctsByCity(city).forEach(x -> puncts.add(x.getAddress()));
        return puncts;
     }

    public List<Car> getCarsForCity(String name){

        List<Car> cars = new ArrayList<>();
        City city = citiesRepo.findCityByName(name).get(); //todo ^

        String[] avaiableCars = city.getAvailableCars().split("-");

        for(String s : avaiableCars){
            cars.add(carsRepo.findById(Integer.parseInt(s)));
        }

        return cars;
    }

}

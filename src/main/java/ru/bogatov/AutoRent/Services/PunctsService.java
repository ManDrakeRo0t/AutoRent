package ru.bogatov.AutoRent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bogatov.AutoRent.Dao.CarsRepo;
import ru.bogatov.AutoRent.Dao.CitiesRepo;
import ru.bogatov.AutoRent.Dao.PunctsRepo;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.City;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PunctsService implements PunctsServiceable {
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

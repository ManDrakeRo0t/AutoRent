package ru.bogatov.AutoRent.Services;

import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.City;

import java.util.List;
import java.util.Map;

public interface PunctsServiceable {
    List<String> getCitiesForCar(Integer id);
    Iterable<City> getAllCities();
    List<String> getPunctsForCity(String name);
    List<Car> getCarsForCity(String name);
    Map<String,List<String>> getCitiesForCarsMap();
    void deleteCar(String id);
}

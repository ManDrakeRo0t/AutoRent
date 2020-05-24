package ru.bogatov.AutoRent.Services;

import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.CarClass;

import java.util.List;

public interface CarServiceable {
    Iterable<Car> getAllCars();
    Iterable<Car> getFilteredCars(Integer max);
    public Car getCar(String str);
    public List<CarClass> getCarClasses();
}

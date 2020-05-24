package ru.bogatov.AutoRent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bogatov.AutoRent.Dao.CarClassesRepo;
import ru.bogatov.AutoRent.Dao.CarsRepo;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.CarClass;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarsRepo carsRepo;
    @Autowired
    CarClassesRepo carClassesRepo;



    public Iterable<Car> getAllCars(){
        return carsRepo.findAll();
    }

    public Iterable<Car> getFilteredCars(Integer max){
        return carsRepo.findByPriceLessThan(max);
    }

    public Car getCar(String str){
        String model = str.substring(str.indexOf(" ")).trim();
        return carsRepo.findByModel(model);
    }


    public List<CarClass> getCarClasses(){
        return carClassesRepo.findAll();
    }

}

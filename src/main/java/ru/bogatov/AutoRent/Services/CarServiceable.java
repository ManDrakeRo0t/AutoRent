package ru.bogatov.AutoRent.Services;

import org.springframework.web.multipart.MultipartFile;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.CarClass;
import ru.bogatov.AutoRent.Forms.CarForm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CarServiceable {
    Iterable<Car> getAllCars();
    Iterable<Car> getFilteredCars(Integer max);
    public Car getCar(String str);
    public List<CarClass> getCarClasses();
    public void deleteCar(String id);
    public void saveCar(Map<String,String> form);
    public void addCar(MultipartFile[] file, CarForm carForm) throws IOException;
}

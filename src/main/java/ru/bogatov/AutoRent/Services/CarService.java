package ru.bogatov.AutoRent.Services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import ru.bogatov.AutoRent.Dao.CarClassesRepo;
import ru.bogatov.AutoRent.Dao.CarsRepo;
import ru.bogatov.AutoRent.Dao.CitiesRepo;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.CarClass;
import ru.bogatov.AutoRent.Entities.City;
import ru.bogatov.AutoRent.Forms.CarForm;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CarService implements CarServiceable{

    @Value("${upload.path}")
    private String path;
    @Autowired
    PunctsServiceable punctsService;
    @Autowired
    CarsRepo carsRepo;
    @Autowired
    CarClassesRepo carClassesRepo;
    @Autowired
    CitiesRepo citiesRepo;

    public void addCar(MultipartFile[] file, CarForm carForm) throws IOException {
        Car car = new Car();
        car.setPrice(carForm.price);
        car.setCarClass(carClassesRepo.getByName(carForm.carClass));
        car.setDescription(carForm.description);
        car.setFuel(carForm.fuel);
        car.setGearbox(carForm.gearbox);
        car.setMark(carForm.mark);
        car.setModel(carForm.model);
        car.setPlaces(carForm.places);
        car.setPictures(Integer.toString(file.length));

        File markDir = new File(path+car.getMark());
        File modelDir = new File(path+car.getMark()+"/"+car.getModel());
        if(!markDir.exists()){
            markDir.mkdir();
        }
        if(!modelDir.exists()){
            modelDir.mkdir();
        }
        for(int i = 0;i < file.length;i++){
            file[i].transferTo(new File(modelDir+"/"+ (i+1) +".jpg"));
        }

        carsRepo.save(car);
    }

    public void saveCar(Map<String,String> form){
        String carID = form.get("id");
        Car car = carsRepo.getById(Integer.parseInt(carID));
        car.setPrice(Integer.parseInt(form.get("price")));
        List<City> Avaiablecities = new ArrayList<>();
        Iterable<City> allCities = citiesRepo.findAll();
        AtomicInteger count = new AtomicInteger(0);
        allCities.forEach(x -> {x.setAvailableCars(x.getAvailableCars().replace("-"+carID+"-","-"));
            count.getAndIncrement();
        });
        for(int i = 0;i <= count.get();i++){
            if(form.containsKey("c"+i)) Avaiablecities.add(citiesRepo.getById(i));
        }
        Avaiablecities.forEach( x -> x.setAvailableCars(x.getAvailableCars()+"-"+carID+"-"));
        Avaiablecities.forEach(x -> x.setAvailableCars(x.getAvailableCars().replace("--","-")));
        allCities.forEach(x -> x.setAvailableCars(x.getAvailableCars().replace("--","-")));
        allCities.forEach( x -> citiesRepo.save(x));
        Avaiablecities.forEach(x -> citiesRepo.save(x));
        carsRepo.save(car);

    }

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
    @Transactional
    public void deleteCar(String id){
        carsRepo.deleteById(Integer.parseInt(id));
        punctsService.deleteCar(id);
    }


    public List<CarClass> getCarClasses(){
        return carClassesRepo.findAll();
    }

}

package ru.bogatov.AutoRent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.bogatov.AutoRent.Entities.Car;
import ru.bogatov.AutoRent.Entities.CarClass;
import ru.bogatov.AutoRent.Forms.CarFilter;
import ru.bogatov.AutoRent.Services.CarService;
import ru.bogatov.AutoRent.Services.PunctsService;

import java.util.List;

@Controller
public class CarsController {
    @Autowired
    CarService carService;
    @Autowired
    PunctsService punctsService;

    @GetMapping("/cars")
    public String carsPark(@RequestParam(name = "car",required = false,defaultValue = "def")String car, Model model){
        if(!car.equals("def")){
            Car l = carService.getCar(car);
            if(l == null){
                model.addAttribute("car","машина не найдена");
            }else{
                List<String> cities = punctsService.getCitiesForCar(l.getId());
                model.addAttribute("cities",cities);
                model.addAttribute("car",l);
            }

            return "car";
        }
        Iterable<Car> cars = carService.getAllCars();
        model.addAttribute("cars",cars);
        return "cars";

    }

    @PostMapping("/filter")
    public String filter(CarFilter filter, Model model){

        Iterable<Car> cars;

        if(filter.getMaxPrice() == null){
            cars = carService.getAllCars();
        }else{
            cars = carService.getFilteredCars(filter.getMaxPrice());
        }

        model.addAttribute("cars",cars);

        return "cars";
    }

    @GetMapping("/carClasses")
    public String calClasses(Model model){
        List<CarClass> carClasses = carService.getCarClasses();
        model.addAttribute("classes",carClasses);
        return "carClasses";
    }
}

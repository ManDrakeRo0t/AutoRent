package ru.bogatov.AutoRent.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bogatov.AutoRent.Dao.Repositories.CitiesRepo;
import ru.bogatov.AutoRent.Entities.City;

@Controller

public class MainController {
    @Autowired
    CitiesRepo citiesRepo;

    @GetMapping("")
    public String home(Model model){
        Iterable<City> cities = citiesRepo.findAll();

        model.addAttribute("cq",cities);
        return "home";
    }

    @GetMapping("/main")
    public String main(){

        return "main";
    }


}

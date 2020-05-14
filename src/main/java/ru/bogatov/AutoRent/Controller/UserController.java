package ru.bogatov.AutoRent.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bogatov.AutoRent.Dao.Repositories.UsersRepo;
import ru.bogatov.AutoRent.Entities.Role;
import ru.bogatov.AutoRent.Entities.User;

import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/registration")
    public String registration(Model model){

        model.addAttribute("msg","");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userBD = usersRepo.findByUsername(user.getUsername());

        if(userBD != null){
            model.addAttribute("msg","Такой пользователь уже существует");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        usersRepo.save(user);
        return "redirect:/login";
    }

}
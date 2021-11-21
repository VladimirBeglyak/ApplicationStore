package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @ModelAttribute
    public User user() {
        return new User();
    }


    @GetMapping
    public String showUserPage() {
        return "user-info";
    }

    @PostMapping("/save")
    public String saveUser(@Valid User user, BindingResult bindingResult, @RequestParam("count") Long id) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            System.out.println(user);
            System.out.println("count = " + id);
            return "user-info";
        }
        System.out.println("count = " + id);
        System.out.println("User was being save!");
        return "catalog";
    }
}

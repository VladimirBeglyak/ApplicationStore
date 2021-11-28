package by.beglyakdehterenok.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/access-denied")
    public String showErrorPageAccessDenied(){
        return "error-page";
    }

    @GetMapping("/money")
    public String showErrorPageNotEnoughMoney(){
        return "error-order";
    }
}

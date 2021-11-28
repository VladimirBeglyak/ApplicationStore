package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(@CurrentSecurityContext(expression = "authentication.name") Authentication authentication,Model model){
        model.addAttribute("account",authentication);
        return "login";
    }

    @GetMapping("/success")
    public String auth(){
        return "redirect:/catalog/";
    }
}

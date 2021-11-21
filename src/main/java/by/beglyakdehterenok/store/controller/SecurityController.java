package by.beglyakdehterenok.store.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sec")
public class SecurityController {


    @GetMapping
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String showUserPage(Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user",userName);
        return "user";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public String showAdminPage(Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("admin",userName);
        return "admin";
    }
}

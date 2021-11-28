package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class IndexPageController {

    @GetMapping
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/contact")
    public String showContactPage(){
        return "contact";
    }

    @GetMapping("/blog")
    public String showBlogPage(){
        return "blog";
    }

    @GetMapping("/about-us")
    public String showAboutUsPage(){
        return "about";
    }

    @GetMapping("/shop-details")
    public String showShopDetailsPage(){
        return "shop-details";
    }

    @GetMapping("/checkout")
    public String showCheckOutPage(){
        return "checkout";
    }

    @GetMapping("/blog-details")
    public String showBlogDetailsPage(){
        return "blog-details";
    }
}

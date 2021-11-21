package by.beglyakdehterenok.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/shopping-cart")
    public String showShoppingCartPage(){
        return "shopping-cart";
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

package by.beglyakdehterenok.store.controller;


import by.beglyakdehterenok.store.entity.Cart;
import by.beglyakdehterenok.store.entity.Order;
import by.beglyakdehterenok.store.service.AccountService;
import by.beglyakdehterenok.store.service.ClothingService;
import by.beglyakdehterenok.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
@SessionAttributes("cart")
public class OrderController {

    private final OrderService orderService;
    private final AccountService accountService;
    private final ClothingService clothingService;

    @Autowired
    public OrderController(OrderService orderService, AccountService accountService, ClothingService clothingService) {
        this.orderService = orderService;
        this.accountService = accountService;
        this.clothingService = clothingService;
    }

    @ModelAttribute("cart")
    public Cart addAttributeCart(){
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCart(){
        return "shopping-cart";
    }


    @PostMapping("/add-to-cart")
    public RedirectView save(@RequestParam("nameOfChooseClothing") String name,
                             @RequestParam("choosedSize") String size,
                             @RequestParam("quantity") Long quantity,
                             @ModelAttribute("cart") Cart cart,
                             @CurrentSecurityContext(expression = "authentication.name") Authentication authentication,
                             RedirectAttributes redirectAttributes){
        if (authentication==null){
            return new RedirectView("/auth/login");
        }

        Order order = orderService.addNewOrderToCart(name, size, quantity, authentication.getName());
        cart.getOrders().add(order);
        Double totalSumByOrderInCart = orderService.getTotalSumByOrderInCart(cart.getOrders());
        redirectAttributes.addFlashAttribute("totalSumInCart",totalSumByOrderInCart);

        return new RedirectView("/order/shopping-cart");
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderFromCart(@PathVariable("id") Long id, @ModelAttribute("cart") Cart cart,Model model){
        Order deletedOrder = cart.getOrders().stream()
                .filter(order -> order.getClothing().getId().equals(id))
                .findFirst().get();

        cart.getOrders().remove(deletedOrder);

        return "redirect:/order/shopping-cart";
    }



    @PostMapping("/new")
    public String save(@ModelAttribute ("newOrder") Order order,
                       @CurrentSecurityContext(expression = "authentication.name") Authentication authentication,
                       @RequestParam("clothingId") Long clothingId,
                       Model model){

        String login = authentication.getName();
        Long id = accountService.getAccountIdByLogin(login);
//        orderService.addNewOrder(id,clothingId,order);

        return "redirect:/catalog";
    }


//    @GetMapping("/allOrders")
//    public ModelAndView showOrders(@RequestParam("account") String name, ModelAndView modelAndView){
//        Order order = orderRepository.findByAccountFirstName(name);
//        modelAndView.addObject("order",order);
//        modelAndView.setViewName("order");
//        return modelAndView;
//    }
}

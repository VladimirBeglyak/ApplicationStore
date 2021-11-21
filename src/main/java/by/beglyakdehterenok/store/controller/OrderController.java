package by.beglyakdehterenok.store.controller;


import by.beglyakdehterenok.store.entity.Order;
import by.beglyakdehterenok.store.service.AccountService;
import by.beglyakdehterenok.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@SessionAttributes("cart")
public class OrderController {

    private final OrderService orderService;
    private final AccountService accountService;

    @Autowired
    public OrderController(OrderService orderService, AccountService accountService) {
        this.orderService = orderService;
        this.accountService = accountService;
    }

    @ModelAttribute
    public List<Order> cart(){
        List<Order> cart = new ArrayList<>();
        return cart;
    }

    @GetMapping("/add-to-cart")
    public String save(@RequestParam("clothingId") Long id,
                       @SessionAttribute("cart") List<Order> cart,
                       Model model){

        model.addAttribute("clothingId",id);
        return "redirect:/catalog";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute ("newOrder") Order order,
                       @CurrentSecurityContext(expression = "authentication.name") Authentication authentication,
                       @RequestParam("clothingId") Long clothingId,
                       Model model){

        String login = authentication.getName();
        Long id = accountService.getAccountIdByLogin(login);
        orderService.addNewOrder(id,clothingId,order);

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

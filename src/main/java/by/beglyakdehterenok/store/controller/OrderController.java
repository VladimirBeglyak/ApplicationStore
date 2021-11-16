package by.beglyakdehterenok.store.controller;


import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.entity.Order;
import by.beglyakdehterenok.store.service.AccountService;
import by.beglyakdehterenok.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final AccountService accountService;

    @Autowired
    public OrderController(OrderService orderService, AccountService accountService) {
        this.orderService = orderService;
        this.accountService = accountService;
    }

    @ModelAttribute
    public void createNewOrder(Model model){
//        Account account = new Account();
//        account.setId(1L);
        Order order = new Order();
        model.addAttribute("newOrder",order);
//        model.addAttribute("accountId",account);
    }

    @GetMapping("/new")
    public String save(@RequestParam("clothingId") Long id, Model model){
        model.addAttribute("clothingId",id);
        return "order-add";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute ("newOrder") Order order,
                       @CurrentSecurityContext(expression = "authentication.name") Authentication authentication,
                       @RequestParam("clothingId") Long clothingId,
                       Model model){

        String login = ((UsernamePasswordAuthenticationToken) authentication).getName();
        Long id = accountService.getAccountIdByLogin(login);
        orderService.addNewOrder(id,clothingId,order);

        return "redirect:/account";
    }


//    @GetMapping("/allOrders")
//    public ModelAndView showOrders(@RequestParam("account") String name, ModelAndView modelAndView){
//        Order order = orderRepository.findByAccountFirstName(name);
//        modelAndView.addObject("order",order);
//        modelAndView.setViewName("order");
//        return modelAndView;
//    }
}

package by.beglyakdehterenok.store.controller;


import by.beglyakdehterenok.store.model.Cart;
import by.beglyakdehterenok.store.entity.Order;
import by.beglyakdehterenok.store.exception.NotEnoughMoneyException;
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

@Controller
@RequestMapping("/order")
@SessionAttributes("cart")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("cart")
    public Cart addAttributeCart() {
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCart() {
        return "shopping-cart";
    }


    @PostMapping("/add-to-cart")
    public RedirectView save(@RequestParam("nameOfChooseClothing") String name,
                             @RequestParam("choosedSize") String size,
                             @RequestParam("quantity") Long quantity,
                             @ModelAttribute("cart") Cart cart,
                             @CurrentSecurityContext(expression = "authentication.name") Authentication authentication,
                             RedirectAttributes redirectAttributes) {
        if (authentication == null) {
            return new RedirectView("/auth/login");
        }

        Order order = orderService.addNewOrderToCart(name, size, quantity, authentication.getName());
        cart.getOrders().add(order);
        Double totalSumByOrderInCart = orderService.getTotalSumByOrderInCart(cart.getOrders());
        cart.setTotalSum(totalSumByOrderInCart);

        return new RedirectView("/order/shopping-cart");
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderFromCart(@PathVariable("id") Long id, @ModelAttribute("cart") Cart cart) {
        Order deletedOrder = cart.getOrders().stream()
                .filter(order -> order.getClothing().getId().equals(id))
                .findFirst().get();

        cart.getOrders().remove(deletedOrder);

        return "redirect:/order/shopping-cart";
    }


    @GetMapping("/save")
    public RedirectView save(@ModelAttribute("cart") Cart cart,
                             @CurrentSecurityContext(expression = "authentication.name") Authentication authentication,
                             Model model) {

        String login = authentication.getName();

        try {
            orderService.saveOrder(login, cart);
            model.addAttribute("cart", new Cart());
        } catch (NotEnoughMoneyException exception) {
            return new RedirectView("/error/money");
        }
        return new RedirectView("/catalog/");
    }
}

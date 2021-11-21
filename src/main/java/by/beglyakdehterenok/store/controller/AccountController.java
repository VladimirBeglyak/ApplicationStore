package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.service.AccountService;
import by.beglyakdehterenok.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.Connection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;
    private final DataSource dataSource;

    @Autowired
    public AccountController(AccountService accountService, PasswordEncoder passwordEncoder, OrderService orderService, DataSource dataSource) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;

        this.dataSource = dataSource;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<Account> accounts = accountService.findAll().stream()
                .filter(account -> account.getRole().name().equals(Role.USER.name()))
                .collect(Collectors.toList());
        System.out.println(accounts);
        model.addAttribute("allRoles", Role.values());
        model.addAttribute("allGenders", Gender.values());
        model.addAttribute("newAccount", new Account());
        model.addAttribute("user", new User());
        model.addAttribute("allAccounts", accounts);
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAnyAuthority('account:write')")
    public String showAllAccounts() {
        return "accounts";
    }


    @GetMapping("/register")
    public String showPage() {
        return "register";
    }

//    @GetMapping("/account")
//    public ModelAndView account(ModelAndView modelAndView){
//        modelAndView.addObject("message","Hello message from model");
//        Account account = accountRepository.findByFirstName("Olga");
//        modelAndView.addObject("account",account);
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }

    @GetMapping("/account/{id}")
    public ModelAndView account(@PathVariable("id") Long id, ModelAndView modelAndView) {
        Optional<Account> account = accountService.findById(id);
        modelAndView.addObject("account", account.get());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveAccount(@Valid @ModelAttribute("newAccount") Account account, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getErrorCount());
            System.out.println(account);
            return "register";
        }
        accountService.save(account);
        return "shop";
    }

    @GetMapping("/delete")
//    @PreAuthorize("hasAuthority('account:write')")
    public String deleteClothingFromStorage(@RequestParam("accountId") Long id) {
        System.out.println("id = " + id);
        accountService.delete(id);
        return "redirect:/account/all";
    }

    @RequestMapping("/update")
//    @PreAuthorize("hasAuthority('account:write')")
    public String updateInfo(@RequestParam("accountId") Long id, Model model) {
        Account account = accountService.findById(id).get();
        System.out.println(account);
        model.addAttribute("newAccount", account);
        return "register";
    }

    @GetMapping("/details")
//    @PreAuthorize("hasAuthority('account:write')")
    public String showOrderDetailsByAccountId(@RequestParam("accountId") Long id, Model model) {

        List<Order> ordersByAccountId = orderService.findAllByAccountIdOrderByIdDesc(id);
        Double totalSum = orderService.getTotalSumOfOrderByAccountId(ordersByAccountId);
//        Map<Double, DoubleSummaryStatistics> collect = ordersByAccountId.stream()
//                .collect(Collectors.groupingBy(order -> order.getClothing().getPrice(),
//                        Collectors.summarizingDouble(value -> value.getQuantity())));
//
//        double totalSum=0;
//
//        for (Map.Entry<Double, DoubleSummaryStatistics> entry : collect.entrySet()) {
//            totalSum+=entry.getKey().doubleValue()*entry.getValue().getSum();
//        }

        System.out.println(ordersByAccountId);
        model.addAttribute("ordersByAccountId", ordersByAccountId);
        model.addAttribute("totalSumOfOrderByAccount",totalSum);
        return "account-order";
    }
}

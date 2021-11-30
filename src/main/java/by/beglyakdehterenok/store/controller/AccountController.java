package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.service.AccountService;
import by.beglyakdehterenok.store.service.OrderService;
import com.google.cloud.storage.Acl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import by.beglyakdehterenok.store.entity.Role;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final OrderService orderService;

    @Autowired
    public AccountController(AccountService accountService, OrderService orderService) {
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("allRoles", Role.values());
        model.addAttribute("allGenders", Gender.values());
        model.addAttribute("user", new User());
    }

    @GetMapping("/private")
    public String showPrivateCabinet(@CurrentSecurityContext(expression = "authentication.name") Authentication authentication,
                                     Model model) {

        Account account = accountService.findByName(authentication.getName());
        model.addAttribute("account",account);
        return "private-cabinet";

    }

    @GetMapping("/all")
    public String showAllAccounts(Model model,String keyword) {
        return listByPage(1,"firstName","asc",keyword,5,model);
    }

    @GetMapping("/all/page/{pageNumber}")
    public String listByPage(@PathVariable ("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             @Param("size") int size,
                             Model model){

        Page<Account> page = accountService.getAllPageable(currentPage,sortField,sortDir,keyword,size);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Account> allAccounts = page.getContent();


        model.addAttribute("allAccounts",allAccounts);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir",reverseSortDir);

        return "accounts";
    }


    @GetMapping("/register")
    public String showPage(Model model) {
        model.addAttribute("newAccount",new Account());
        return "register";
    }

    @GetMapping("/top-up-account")
    public String showPageTopUpAccount(@CurrentSecurityContext(expression = "authentication.name") Authentication authentication) {
        if (authentication == null) {
            return "redirect:/auth/login";
        }
        return "add-money-to-account";
    }

    @PostMapping("/top-up-account")
    public RedirectView showPageTopUpAccount(@CurrentSecurityContext(expression = "authentication.name") Authentication authentication,
                                             @RequestParam("accountAmount") Double accountAmount) {
        String login = authentication.getName();
        accountService.topUpAccount(login,accountAmount);
        return new RedirectView("/account/private");
    }

    @PostMapping("/save")
    public String saveAccount(@Valid @ModelAttribute("newAccount") Account account, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println(errors.getErrorCount());
            System.out.println(account);
            return "register";
        }
        accountService.save(account);
        return "redirect:/auth/login";
    }

    @GetMapping("/delete")
    public String deleteClothingFromStorage(@RequestParam("accountId") Long id) {
        System.out.println("id = " + id);
        accountService.delete(id);
        return "redirect:/account/all";
    }

    @RequestMapping("/update")
    public String updateInfo(@RequestParam("accountId") Long id, Model model) {
        Account account = accountService.findById(id).get();
        System.out.println(account);
        model.addAttribute("newAccount", account);
        return "register";
    }

    @GetMapping("/details")
    public String showOrderDetailsByAccountId(@RequestParam("accountId") Long id, Model model) {

        List<Order> ordersByAccountId = orderService.findAllByAccountIdOrderByIdDesc(id);
        Double totalSum = orderService.getTotalSumOfOrderByAccountId(ordersByAccountId);

        System.out.println(ordersByAccountId);
        model.addAttribute("ordersByAccountId", ordersByAccountId);
        model.addAttribute("totalSumOfOrderByAccount",totalSum);
        return "account-order";
    }
}

package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.entity.Gender;
import by.beglyakdehterenok.store.entity.Role;
import by.beglyakdehterenok.store.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/start")
    public ModelAndView showPage(ModelAndView modelAndView){
        Account account = new Account();
        modelAndView.addObject("allRoles", Role.values());
        modelAndView.addObject("allGenders", Gender.values());
        modelAndView.addObject("newAccount",account);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @GetMapping("/account")
    public ModelAndView account(ModelAndView modelAndView){
        modelAndView.addObject("message","Hello message from model");
        Account account = accountRepository.findByFirstName("Olga");
        modelAndView.addObject("account",account);
        modelAndView.setViewName("start-page");
        return modelAndView;
    }

    @GetMapping("/account/{id}")
    public ModelAndView account(@PathVariable("id") Long id, ModelAndView modelAndView){
        Optional<Account> account = accountRepository.findById(id);
        modelAndView.addObject("account",account.get());
        modelAndView.setViewName("start-page");
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Account account){
        System.out.println(account);
        accountRepository.save(account);
        return "redirect:/start";
    }
}

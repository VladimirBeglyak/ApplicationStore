package by.beglyakdehterenok.store.controller;

import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.entity.Gender;
import by.beglyakdehterenok.store.entity.Role;
import by.beglyakdehterenok.store.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public ModelAndView showPage(/*@Valid*/ ModelAndView modelAndView, BindingResult bindingResult){
        Account account = new Account();
        modelAndView.addObject("allRoles", Role.values());
        modelAndView.addObject("allGenders", Gender.values());
        modelAndView.addObject("newAccount",account);
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("register");
            return modelAndView;
        } else {
            modelAndView.setViewName("register");
            return modelAndView;
        }

    }

    @GetMapping("/account")
    public ModelAndView account(ModelAndView modelAndView){
        modelAndView.addObject("message","Hello message from model");
        Account account = accountRepository.findByFirstName("Olga");
        modelAndView.addObject("account",account);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/account/{id}")
    public ModelAndView account(@PathVariable("id") Long id, ModelAndView modelAndView){
        Optional<Account> account = accountRepository.findById(id);
        modelAndView.addObject("account",account.get());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Account account){
        System.out.println(account);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        System.out.println(account.getPassword());
        accountRepository.save(account);
        return "redirect:/shop/";
    }
}

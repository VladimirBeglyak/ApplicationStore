package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.repository.AccountRepository;
import by.beglyakdehterenok.store.repository.OrderRepository;
import by.beglyakdehterenok.store.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryTest extends BaseTest<Account> {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;


    @Test
    @Override
    List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Test
    @Override
    public void save() {
        Account account = new Account();
        account.setFirstName("Vladimir");
        account.setLastName("Beglyak");
        account.setRole(Role.ADMIN);
        account.setGender(Gender.MALE);
        account.setAccountAmount(10);
        account.setAddress(new Address("Belarus", "Minsk", "Kozlova", "10"));
        account.setBirthday(Date.valueOf(LocalDate.of(1990, Month.JANUARY, 10)));
        account.setEmail("test1@mail.ru");
        account.setLogin("qwerty");
        account.setPhoneNumber("+375336790383");
        account.setPassword("123456");

        accountRepository.save(account);

    }

    @Test
    @Override
    void delete() {
        accountRepository.deleteById(1L);
    }

    @Test
    @Override
    public Optional<Account> findById(){
        Optional<Account> account = accountRepository.findById(1L);
        System.out.println(account);
        System.out.println(account.get().getRole().getPermissions());
        System.out.println(account.get().getRole().getAuthorities());
        return account;
    }

    @Test
    @Override
    public void update(){
        Account account = accountRepository.findById(1L).get();
        account.setLastName("TEST");
        accountRepository.saveAndFlush(account);
    }


}

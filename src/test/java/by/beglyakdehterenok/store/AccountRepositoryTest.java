package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.repository.AccountRepository;
import by.beglyakdehterenok.store.repository.OrderRepository;
import by.beglyakdehterenok.store.repository.StorageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void addNewAccount() {
        Account account = new Account();
        account.setFirstName("Vladimir");
        account.setLastName("Beglyak");
        account.setRole(Role.ADMIN);
        account.setGender(Gender.MALE);
        account.setAccountAmount(10);
        account.setAddress(new Address("Belarus", "Minsk", "Kozlova", "10"));
        account.setBirthday(LocalDate.of(1990, Month.JANUARY, 10));
        account.setEmail("test1@mail.ru");
        account.setLogin("qwerty");
        account.setPhoneNumber("+375336790383");
        account.setPassword("123456");

        accountRepository.save(account);

    }

    @Test
    @Transactional
    public void addNewOrderByAccount() {
        Account account = accountRepository.findById(2L).get();
        System.out.println(account);
        Clothing clothing = storageRepository.getOne(1L).getClothing();
        Order order = new Order();
        order.setName("First Order");
        order.setQuantity(1L);
        order.setAccount(account);
        account.addOrder(order);
        order.setClothing(clothing);

        orderRepository.save(order);

        System.out.println();
    }

    @Test
    public void addNewOrderSimple(){
        Order order = new Order();
        order.setQuantity(1L);
        order.setName("First Order");
        orderRepository.save(order);
    }

    @Test
    public void findAllOrder(){
        orderRepository.findAll()
                .forEach(order -> System.out.println(order));
    }


}

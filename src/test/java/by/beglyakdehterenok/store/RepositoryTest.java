package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public class RepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

//    @Test
//    public void test(){
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//    }

    @Test
    public void test2(){
//        Account account = new Account();
//        account.setFirstName("Sergey");
//        account.setLastName("Dubina");
//        account.setRole(Role.USER);
//        account.setGender(Gender.MALE);
//        account.setAddress(new Address("Belarus","Minsk","Rainisa","9"));
//        account.setAccountAmount(54);
//        account.setBirthday(LocalDate.of(1986, Month.MARCH,25));
//        account.setEmail("test3@mail.ru");
//        account.setLogin("sergey1");
//        account.setPassword("edawesf");
//        account.setPhoneNumber("+375294537653");
//        Order order = new Order();
//        order.setQuantity(10);
//        order.setAccount(account);
//        account.addOrder(order);

//        accountRepository.save(account);
//        System.out.println(account);

//        List<Account> allByRole = accountRepository.findAllByRole(Role.ADMIN);
//        System.out.println(allByRole);
//
        List<Account> all = accountRepository.findAll();
        all.forEach(account -> System.out.println(account));

//        accountRepository.save(account);
//        System.out.println(account);
    }
}

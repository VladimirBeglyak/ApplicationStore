package by.beglyakdehterenok.store;

import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.entity.Order;
import by.beglyakdehterenok.store.repository.AccountRepository;
import by.beglyakdehterenok.store.repository.OrderRepository;
import by.beglyakdehterenok.store.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepositoryTest extends BaseTest<Order>{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private OrderService orderService;

    @Test
    @Override
    List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Test
    @Override
    void save() {
        Account account = accountRepository.findById(1L).get();
        System.out.println(account);
        Order order = new Order();
        order.setName("Second Order");
        order.setQuantity(1L);
        order.setAccount(account);
        orderRepository.save(order);
    }

    @Test
    public void saveFromService(){
        Order order = new Order();
        order.setQuantity(4L);
        order.setName("Order");
        orderService.addNewOrder(1L,4L,order);
    }

    @Test
    @Override
    void delete() {
        orderRepository.deleteById(1L);
    }

    @Test
    @Override
    Optional<Order> findById() {
        return orderRepository.findById(1L);
    }

    @Test
    @Override
    void update() {

    }

    @Test
    @Transactional
    public void getTotalSumByOrder(){

        List<Order> ordersByAccountId = orderService.findAllByAccountIdOrderByIdDesc(1L);

        Map<Double, DoubleSummaryStatistics> collect = ordersByAccountId.stream()
                .collect(Collectors.groupingBy(order -> order.getClothing().getPrice(),
                        Collectors.summarizingDouble(value -> value.getQuantity())));


        double sum=0;

        for (Map.Entry<Double, DoubleSummaryStatistics> entry : collect.entrySet()) {
            sum+=entry.getKey().doubleValue()*entry.getValue().getSum();
        }
        System.out.println(sum);
    }
}

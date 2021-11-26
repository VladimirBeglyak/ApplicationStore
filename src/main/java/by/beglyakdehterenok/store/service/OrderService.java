package by.beglyakdehterenok.store.service;

import by.beglyakdehterenok.store.entity.*;
import by.beglyakdehterenok.store.exception.NotEnoughMoneyException;
import by.beglyakdehterenok.store.repository.AccountRepository;
import by.beglyakdehterenok.store.repository.ClothingRepository;
import by.beglyakdehterenok.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final ClothingRepository clothingRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, AccountRepository accountRepository, ClothingRepository clothingRepository) {
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
        this.clothingRepository = clothingRepository;
    }

    @Transactional
    public Order addNewOrderToCart(String nameOfChooseClothing, String size, Long countOfClothing, String login) {
        Clothing clothing = clothingRepository.findByNameAndSize(nameOfChooseClothing, Size.valueOf(size));
        Account account = accountRepository.findByLogin(login).get();
        Order order = new Order();
        order.setClothing(clothing);
        order.setAccount(account);
        order.setQuantity(countOfClothing);
        return order;
    }

    @Transactional
    public Double getTotalSumByOrderInCart(List<Order> orders) {
        return getTotalSumOfOrderByAccountId(orders);
    }

    public List<Order> findAllByAccountIdOrderByIdDesc(Long id) {
        return orderRepository.findAllByAccount_IdOrderById(id);
    }

    public Double getTotalSumOfOrderByAccountId(List<Order> orders) {

        Map<Double, DoubleSummaryStatistics> collect = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getClothing().getPrice(),
                        Collectors.summarizingDouble(value -> value.getQuantity())));

        double totalSum = 0;

        for (Map.Entry<Double, DoubleSummaryStatistics> entry : collect.entrySet()) {
            totalSum += entry.getKey().doubleValue() * entry.getValue().getSum();
        }

        return totalSum;
    }

    @Transactional
    public void saveOrder(String login, Cart cart) throws NotEnoughMoneyException {

        Account account = accountRepository.findByLogin(login).get();
        Double totalSumByAllOrders = getTotalSumByOrderInCart(cart.getOrders());
        Double accountAmount = account.getAccountAmount();
        if (totalSumByAllOrders > accountAmount) {
            throw new NotEnoughMoneyException("Not enough money");
        }
        List<Order> orders = cart.getOrders();
        orders.stream().forEach(order -> order.setDateOfCreate(LocalDateTime.now()));
        account.setAccountAmount(accountAmount - totalSumByAllOrders);
        account.setOrders(orders);
        accountRepository.saveAndFlush(account);

        List<Clothing> clothes = orders.stream()
                .map(order -> order.getClothingByNewCount())
                .collect(Collectors.toList());

//        Map<Clothing, Long> clothingAndCountOfClothingInOrder = orders.stream()
//                .collect(Collectors.toMap(order -> (order.getClothing()), (order -> order.getQuantity())));
//
//        List<Long> newCountOfClothing = new LinkedList<>();
//        for (Map.Entry<Clothing, Long> entry : clothingAndCountOfClothingInOrder.entrySet()) {
//            newCountOfClothing.addFirst(entry.getKey().getCount() - entry.getValue());
//        }


        clothingRepository.saveAll(clothes);

        System.out.println(orders);
    }

//    //считает общую сумму всех заказов в корзине
//    public double countByFinalPriceWhereAccount_Id(Long id){
//        return orderRepository.countByFinalPriceWhereAccount_Id(id);
//    }
//
//    //если заказ существует - обновляет воличесто и итоговую цену
//    public void saveOrUpdate(Order order, Long accountId){
//        if (orderRepository.existsByClothing_NameAndAccount_Id(order.getClothing().getName(), accountId)) {
//            orderRepository.updateExistOrder(order.getQuantity(), accountId, order.getClothing().getName());
//        } else {
//            orderRepository.save(order);
//        }
//    }
//
//

}

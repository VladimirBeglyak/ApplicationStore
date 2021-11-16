package by.beglyakdehterenok.store.service;

import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Order;
import by.beglyakdehterenok.store.entity.Storage;
import by.beglyakdehterenok.store.repository.AccountRepository;
import by.beglyakdehterenok.store.repository.ClothingRepository;
import by.beglyakdehterenok.store.repository.OrderRepository;
import by.beglyakdehterenok.store.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final StorageRepository storageRepository;
    private final ClothingRepository clothingRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, AccountRepository accountRepository, StorageRepository storageRepository, ClothingRepository clothingRepository) {
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
        this.storageRepository = storageRepository;
        this.clothingRepository = clothingRepository;
    }

    @Transactional
    public void addNewOrder(Long accountId,Long clothingId,Order order){
        Account account = accountRepository.getOne(accountId);
        Storage storage = storageRepository.getOne(clothingId);
        order.setAccount(account);
        account.addOrder(order);
        order.setClothing(storage.getClothing());
        orderRepository.save(order);
        storage.setCount(storage.getCount()-order.getQuantity());
        storageRepository.flush();
    }

//    public Order findById(Long id){
//        return orderRepository.findById(id).get();
//    }
//
//    public List<Order> findAllByAccountIdOrderByIdDesc(Long id){
//        return orderRepository.findAllByAccount_IdOOrderById(id);
//    }
//
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

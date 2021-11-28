package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByAccount_IdOrderById(Long id);

}

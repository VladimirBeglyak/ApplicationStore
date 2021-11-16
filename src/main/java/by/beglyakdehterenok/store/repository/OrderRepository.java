package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
//    Optional<Order> findById(Long id);
//    Optional<Order> findOrderByAccount_Id(Long id);
//    List<Order> findAllByAccount_IdOOrderById(Long id);
//    double countByFinalPriceWhereAccount_Id(Long id);
//    boolean existsByClothing_NameAndAccount_Id(String name,Long id);
//    int updateExistOrder(Integer quantity,Long id,String name);
}

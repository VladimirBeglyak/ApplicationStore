package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByFirstName(String name);
    Optional<Account> findById(Long id);

}

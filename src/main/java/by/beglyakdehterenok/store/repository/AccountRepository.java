package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long>, CrudRepository<Account,Long>, Repository<Account,Long> {

    Account findByFirstName(String name);

    Optional<Account> findById(Long id);

    Optional<Account> findByLogin(String login);

    @Query("select a.email from Account a")
    List<String> findAllEmails();


}

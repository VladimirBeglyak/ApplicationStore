package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long>,
        PagingAndSortingRepository<Account,Long> {

    Optional<Account> findById(Long id);

    Optional<Account> findByLogin(String login);

    @Query("select a from Account a where " +
            " concat(a.firstName,' ',a.lastName,' ',a.email) " +
            "like %?1%")
    Page<Account> findAll(String keyword, Pageable pageable);

}

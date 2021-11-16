package by.beglyakdehterenok.store.service;

import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.mapper.AccountMapperImpl;
import by.beglyakdehterenok.store.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements BaseService<Account,Long> {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public Account findByFirstName(String name){
        return accountRepository.findByFirstName(name);
    }

    public Account findByLogin(String login){
        return accountRepository.findByLogin(login).get();
    }

    public Long getAccountIdByLogin(String login){
        return new AccountMapperImpl().mapFrom(accountRepository.findByLogin(login).get()).getId();
    }
}

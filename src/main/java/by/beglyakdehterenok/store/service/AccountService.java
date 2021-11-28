package by.beglyakdehterenok.store.service;

import by.beglyakdehterenok.store.dto.AccountDto;
import by.beglyakdehterenok.store.entity.Account;
import by.beglyakdehterenok.store.mapper.AccountMapperImpl;
import by.beglyakdehterenok.store.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements BaseService<Account, Long> {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void save(Account account) {
        if (account.getId() == null) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
        } else {
            accountRepository.saveAndFlush(account);
        }
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public void topUpAccount(String login, Double accountAmount) {
        Account account = accountRepository.findByLogin(login).get();
        Double currentMoney = account.getAccountAmount();
        currentMoney += accountAmount;
        account.setAccountAmount(currentMoney);
        accountRepository.saveAndFlush(account);
    }


    public Page<Account> getAllPageable(int pageNumber,
                                        String sortField,
                                        String sortDir,
                                        String keyword,
                                        int size) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, size, sort);

        if (keyword != null) {
           return accountRepository.findAll(keyword, pageable);
        }
        return accountRepository.findAll(pageable);
    }
}

package by.beglyakdehterenok.store.mapper;

import by.beglyakdehterenok.store.dto.AccountDto;
import by.beglyakdehterenok.store.entity.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements BaseMapper<Account, AccountDto>{

    @Override
    public AccountDto mapFrom(Account entity) {
        return new AccountDto(entity.getId(),entity.getLogin(), entity.getAccountAmount());
    }
}

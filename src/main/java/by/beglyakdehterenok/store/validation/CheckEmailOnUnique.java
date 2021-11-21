package by.beglyakdehterenok.store.validation;

import by.beglyakdehterenok.store.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmailOnUnique implements ConstraintValidator<CheckEmail, String> {

    private final AccountRepository accountRepository;

    @Autowired
    public CheckEmailOnUnique(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void initialize(CheckEmail constraint) {

    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return false;
    }
}

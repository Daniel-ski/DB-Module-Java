package com.example.springbootintro.services;

import com.example.springbootintro.models.Account;
import com.example.springbootintro.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withDrawMoney(BigDecimal money, Long id) {
        Optional<Account> account = this.accountRepository.findById(id);

        if (account.isEmpty()){
            throw new RuntimeException("No exist account");
        }

        BigDecimal currentBalance = account.get().getBalance();

        if (currentBalance.compareTo(money) > 0 ){
            throw new UnsupportedOperationException("Insufficient availability");
        }

        account.get().setBalance(currentBalance.subtract(money));

        this.accountRepository.save(account.get());
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No exist account"));

        if (money.compareTo(BigDecimal.ZERO) < 0 ){
            throw new UnsupportedOperationException();
        }

        BigDecimal balanceToAdd = account.getBalance().add(money);

        account.setBalance(balanceToAdd);

        this.accountRepository.save(account);

    }
}

package com.example.springbootintro;

import com.example.springbootintro.models.User;
import com.example.springbootintro.services.AccountService;
import com.example.springbootintro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final AccountService accountService ;
    private final UserService userService;

    @Autowired
    public ConsoleRunner(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
      //  this.userService.register("Daniel",27);

        User user = this.userService.findByUserName("Daniel");

        this.accountService.depositMoney(BigDecimal.valueOf(10000), user.getId());
    }
}

package com.example.springbootintro.services;

import java.math.BigDecimal;

public interface AccountService {
    void withDrawMoney(BigDecimal money,Long id);
    void depositMoney(BigDecimal money, Long id);
}

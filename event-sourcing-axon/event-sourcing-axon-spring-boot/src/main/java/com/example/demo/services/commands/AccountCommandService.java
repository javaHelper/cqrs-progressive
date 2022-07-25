package com.example.demo.services.commands;

import java.util.concurrent.CompletableFuture;

import com.example.demo.dto.commands.AccountCreateDTO;
import com.example.demo.dto.commands.MoneyCreditDTO;
import com.example.demo.dto.commands.MoneyDebitDTO;

public interface AccountCommandService {

	public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);

	public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);

	public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
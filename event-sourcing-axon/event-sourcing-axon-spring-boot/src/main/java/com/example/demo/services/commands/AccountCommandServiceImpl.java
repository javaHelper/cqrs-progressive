package com.example.demo.services.commands;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.commands.CreateAccountCommand;
import com.example.demo.commands.CreditMoneyCommand;
import com.example.demo.commands.DebitMoneyCommand;
import com.example.demo.dto.commands.AccountCreateDTO;
import com.example.demo.dto.commands.MoneyCreditDTO;
import com.example.demo.dto.commands.MoneyDebitDTO;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

	@Autowired
	private CommandGateway commandGateway;

	@Override
	public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
		CreateAccountCommand command = new CreateAccountCommand(UUID.randomUUID().toString(),
				accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency());

		return commandGateway.send(command);
	}

	@Override
	public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
		CreditMoneyCommand command = new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(),
				moneyCreditDTO.getCurrency());
		return commandGateway.send(command);
	}

	@Override
	public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
		DebitMoneyCommand command = new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(),
				moneyDebitDTO.getCurrency());
		return commandGateway.send(command);
	}
}
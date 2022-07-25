package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AccountQueryEntity;
import com.example.demo.services.AccountQueryService;

@RestController
@RequestMapping(value = "/bank-accounts")
public class AccountQueryController {

	@Autowired
	private AccountQueryService accountQueryService;

	@GetMapping("/{accountNumber}")
	public AccountQueryEntity getAccount(@PathVariable(value = "accountNumber") String accountNumber) {
		return accountQueryService.getAccount(accountNumber);
	}

	@GetMapping("/{accountNumber}/events")
	public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber) {
		return accountQueryService.listEventsForAccount(accountNumber);
	}
}
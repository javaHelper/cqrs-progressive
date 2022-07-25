package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.query.AccountQueryService;

@RestController
@RequestMapping(value = "/bank-accounts")
public class AccountQueryController {

	@Autowired
	private AccountQueryService accountQueryService;

	@GetMapping("/{accountNumber}/events")
	public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber) {
		return accountQueryService.listEventsForAccount(accountNumber);
	}
}
package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AccountQueryEntity;
import com.example.demo.repositories.AccountRepository;

@Service
public class AccountQueryService {

	@Autowired
	private EventStore eventStore;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Object> listEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber)
        		.asStream()
        		.map( s -> s.getPayload())
        		.collect(Collectors.toList());
    }
	
	public AccountQueryEntity getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }
}

package com.example.demo.config;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.aggregates.AccountAggregate;;

@Configuration
public class AxonConfig {
	
	@Autowired
	private EventStore eventStore;

	@Bean
	EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository() {
		return EventSourcingRepository
				.builder(AccountAggregate.class)
				.eventStore(eventStore).build();
	}
}
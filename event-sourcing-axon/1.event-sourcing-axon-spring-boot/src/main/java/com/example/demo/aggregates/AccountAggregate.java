package com.example.demo.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.demo.commands.CreateAccountCommand;
import com.example.demo.commands.CreditMoneyCommand;
import com.example.demo.commands.DebitMoneyCommand;
import com.example.demo.events.AccountActivatedEvent;
import com.example.demo.events.AccountCreatedEvent;
import com.example.demo.events.AccountHeldEvent;
import com.example.demo.events.MoneyCreditedEvent;
import com.example.demo.events.MoneyDebitedEvent;

import lombok.Data;

@Data
@Aggregate
public class AccountAggregate {
	@AggregateIdentifier
    private String id;

    private double accountBalance;

    private String currency;

    private String status;
    
    public AccountAggregate() {}
    
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
    	AccountCreatedEvent event = new AccountCreatedEvent(command.id, command.accountBalance, command.currency);
    	AggregateLifecycle.apply(event);
    }
    
    @CommandHandler
    public void on(CreditMoneyCommand command) {
    	MoneyCreditedEvent event = new MoneyCreditedEvent(command.id, command.creditAmount, command.currency);
    	AggregateLifecycle.apply(event);
    }
    
    @CommandHandler
    protected void on(DebitMoneyCommand command){
    	MoneyDebitedEvent event = new MoneyDebitedEvent(command.id, command.debitAmount, command.currency);
        AggregateLifecycle.apply(event);
    }
    
    
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
    	this.id = event.id;
    	this.accountBalance = event.accountBalance;
    	this.currency = event.currency;
    	this.status = String.valueOf(Status.CREATED);
    	
    	AccountActivatedEvent accountActivatedEvent = new AccountActivatedEvent(this.id, Status.ACTIVATED);
    	AggregateLifecycle.apply(accountActivatedEvent);
    }
    
    
    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
    	this.status = String.valueOf(Status.ACTIVATED);
    }
    
    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent) {
    	if(this.accountBalance < 0 && (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0) {
    		AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
    	}
    	
    	this.accountBalance += moneyCreditedEvent.creditAmount;
    }
    
    
    @EventSourcingHandler
    protected void on(MoneyDebitedEvent moneyDebitedEvent){
        if (this.accountBalance >= 0 & (this.accountBalance - moneyDebitedEvent.debitAmount) < 0){
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
        }
        this.accountBalance -= moneyDebitedEvent.debitAmount;

    }

    @EventSourcingHandler
    protected void on(AccountHeldEvent accountHeldEvent){
        this.status = String.valueOf(accountHeldEvent.status);
    }
}

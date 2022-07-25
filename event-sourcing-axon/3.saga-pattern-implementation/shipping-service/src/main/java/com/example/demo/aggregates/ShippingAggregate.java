package com.example.demo.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.demo.commands.CreateShippingCommand;
import com.example.demo.events.OrderShippedEvent;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Aggregate
public class ShippingAggregate {

    @AggregateIdentifier
    private String shippingId;

    private String orderId;

    private String paymentId;

    public ShippingAggregate() {
    }

    @CommandHandler
    public ShippingAggregate(CreateShippingCommand createShippingCommand){
    	OrderShippedEvent orderShippedEvent = new OrderShippedEvent(createShippingCommand.shippingId, 
    			createShippingCommand.orderId, 
    			createShippingCommand.paymentId);
    	
        AggregateLifecycle.apply(orderShippedEvent);
    }

    @EventSourcingHandler
    protected void on(OrderShippedEvent orderShippedEvent){
        this.shippingId = orderShippedEvent.shippingId;
        this.orderId = orderShippedEvent.orderId;
    }
}
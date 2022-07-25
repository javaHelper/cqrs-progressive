package com.example.demo.aggregates;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.demo.commands.CreateOrderCommand;
import com.example.demo.commands.UpdateOrderStatusCommand;
import com.example.demo.events.OrderCreatedEvent;
import com.example.demo.events.OrderUpdatedEvent;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    private ItemType itemType;

    private BigDecimal price;

    private String currency;

    private OrderStatus orderStatus;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand){
    	OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(createOrderCommand.orderId, 
    			createOrderCommand.itemType,
                createOrderCommand.price, 
                createOrderCommand.currency, 
                createOrderCommand.orderStatus);
    	
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    protected void on(OrderCreatedEvent orderCreatedEvent){
        this.orderId = orderCreatedEvent.orderId;
        this.itemType = ItemType.valueOf(orderCreatedEvent.itemType);
        this.price = orderCreatedEvent.price;
        this.currency = orderCreatedEvent.currency;
        this.orderStatus = OrderStatus.valueOf(orderCreatedEvent.orderStatus);
    }

    @CommandHandler
    protected void on(UpdateOrderStatusCommand updateOrderStatusCommand){
    	OrderUpdatedEvent orderUpdatedEvent = new OrderUpdatedEvent(updateOrderStatusCommand.orderId, 
    			updateOrderStatusCommand.orderStatus);
    	
        AggregateLifecycle.apply(orderUpdatedEvent);
    }

    @EventSourcingHandler
    protected void on(OrderUpdatedEvent orderUpdatedEvent){
        this.orderId = orderUpdatedEvent.getOrderId();
        this.orderStatus = OrderStatus.valueOf(orderUpdatedEvent.orderStatus);
    }
}
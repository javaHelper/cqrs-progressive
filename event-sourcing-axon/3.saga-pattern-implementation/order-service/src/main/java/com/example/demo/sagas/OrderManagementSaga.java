package com.example.demo.sagas;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.aggregates.OrderStatus;
import com.example.demo.commands.CreateInvoiceCommand;
import com.example.demo.commands.CreateShippingCommand;
import com.example.demo.commands.UpdateOrderStatusCommand;
import com.example.demo.events.InvoiceCreatedEvent;
import com.example.demo.events.OrderCreatedEvent;
import com.example.demo.events.OrderShippedEvent;
import com.example.demo.events.OrderUpdatedEvent;

@Saga
public class OrderManagementSaga {
	@Autowired
    private CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent){
        String paymentId = UUID.randomUUID().toString();
        System.out.println("### Saga invoked");

        //associate Saga
        SagaLifecycle.associateWith("paymentId", paymentId);

        System.out.println("order id => " + orderCreatedEvent.orderId);

        //send the commands
        CreateInvoiceCommand createInvoiceCommand = new CreateInvoiceCommand(paymentId, 
        		orderCreatedEvent.orderId);
        
        commandGateway.send(createInvoiceCommand);
    }

    @SagaEventHandler(associationProperty = "paymentId")
    public void handle(InvoiceCreatedEvent invoiceCreatedEvent){
        String shippingId = UUID.randomUUID().toString();

        System.out.println("## Saga continued");

        //associate Saga with shipping
        SagaLifecycle.associateWith("shipping", shippingId);

        //send the create shipping command
        CreateShippingCommand createShippingCommand = new CreateShippingCommand(shippingId, 
        		invoiceCreatedEvent.orderId, 
        		invoiceCreatedEvent.paymentId);
        
        commandGateway.send(createShippingCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent orderShippedEvent){
    	UpdateOrderStatusCommand updateOrderStatusCommand = new UpdateOrderStatusCommand(orderShippedEvent.orderId, 
    			String.valueOf(OrderStatus.SHIPPED));
    	
        commandGateway.send(updateOrderStatusCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderUpdatedEvent orderUpdatedEvent){
        SagaLifecycle.end();
    }
}
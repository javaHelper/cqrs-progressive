package com.example.demo.service;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.aggregates.OrderStatus;
import com.example.demo.commands.CreateOrderCommand;
import com.example.demo.dto.commands.OrderCreateDTO;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

	@Autowired
    private CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO) {
    	CreateOrderCommand createOrderCommand = new CreateOrderCommand(
    			UUID.randomUUID().toString(), 
    			orderCreateDTO.getItemType(),
                orderCreateDTO.getPrice(), 
                orderCreateDTO.getCurrency(), 
                String.valueOf(OrderStatus.CREATED));
    	
        return commandGateway.send(createOrderCommand);
    }
}
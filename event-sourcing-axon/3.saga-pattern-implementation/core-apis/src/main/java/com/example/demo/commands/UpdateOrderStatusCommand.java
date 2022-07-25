package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateOrderStatusCommand {
	@TargetAggregateIdentifier
	public String orderId;
	public String orderStatus;
}
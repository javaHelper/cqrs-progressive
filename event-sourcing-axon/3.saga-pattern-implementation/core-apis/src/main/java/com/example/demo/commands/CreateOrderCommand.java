package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderCommand {

	@TargetAggregateIdentifier
	public String orderId;
	public String itemType;
	public BigDecimal price;
	public String currency;
	public String orderStatus;
}
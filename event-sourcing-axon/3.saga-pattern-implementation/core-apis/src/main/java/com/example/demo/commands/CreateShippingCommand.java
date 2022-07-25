package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateShippingCommand {
	@TargetAggregateIdentifier
	public String shippingId;
	public String orderId;
	public String paymentId;
}
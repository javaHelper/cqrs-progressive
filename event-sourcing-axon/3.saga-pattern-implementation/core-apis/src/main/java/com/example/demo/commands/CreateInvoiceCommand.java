package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceCommand {
	@TargetAggregateIdentifier
	public String paymentId;
	public String orderId;
}
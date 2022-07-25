package com.example.demo.events;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
	public String orderId;
	public String itemType;
	public BigDecimal price;
	public String currency;
	public String orderStatus;
}
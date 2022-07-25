package com.example.demo.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderShippedEvent {
	public String shippingId;
	public String orderId;
	public String paymentId;
}
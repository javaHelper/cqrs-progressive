package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.commands.OrderCreateDTO;
import com.example.demo.service.OrderCommandService;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderCommandController {

	@Autowired
	private OrderCommandService orderCommandService;

	@PostMapping
	public CompletableFuture<String> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
		return orderCommandService.createOrder(orderCreateDTO);
	}
}
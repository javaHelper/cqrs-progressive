package com.example.demo.events;

import com.example.demo.aggregates.Status;

public class AccountHeldEvent extends BaseEvent<String> {

	public final Status status;

	public AccountHeldEvent(String id, Status status) {
		super(id);
		this.status = status;
	}
}
package com.example.demo.events;

import com.example.demo.aggregates.Status;

public class AccountActivatedEvent extends BaseEvent<String> {

	public final Status status;

	public AccountActivatedEvent(String id, Status status) {
		super(id);
		this.status = status;
	}
}
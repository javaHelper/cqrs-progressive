package com.example.demo.services.query;

import java.util.List;

public interface AccountQueryService {
	public List<Object> listEventsForAccount(String accountNumber);
}
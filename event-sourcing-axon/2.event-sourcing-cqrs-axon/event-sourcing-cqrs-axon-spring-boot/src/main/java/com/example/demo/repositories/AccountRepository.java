package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.AccountQueryEntity;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {

}
package com.hidalgocarlos.zonafitspringboot.repository;

import com.hidalgocarlos.zonafitspringboot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
}

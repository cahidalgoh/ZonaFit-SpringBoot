package com.hidalgocarlos.ZonaFitSpringBoot.repository;

import com.hidalgocarlos.ZonaFitSpringBoot.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
}

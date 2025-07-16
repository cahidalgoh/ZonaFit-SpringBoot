package com.hidalgocarlos.ZonaFitSpringBoot.service;

import com.hidalgocarlos.ZonaFitSpringBoot.model.Customer;

import java.util.List;

public interface ICustomerService {

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(Integer customerId);

    public void saveOrUpdateCustomer(Customer customer);

    public void deleteCustomer(Customer customer);

}

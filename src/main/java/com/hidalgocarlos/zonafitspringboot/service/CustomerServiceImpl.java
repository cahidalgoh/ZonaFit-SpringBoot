package com.hidalgocarlos.zonafitspringboot.service;

import com.hidalgocarlos.zonafitspringboot.model.Customer;
import com.hidalgocarlos.zonafitspringboot.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    /**
     * Returns a list of existing customers in the database
     * @return a list of customers
     */
    @Override
    public List<Customer> getAllCustomers() {
        // Se obtiene los clientes de la base de datos.
        List<Customer> customers;
        customers = customerRepository.findAll();
        return customers;
    }

    /**
     * @return Returns the customer if it exists, otherwise returns a null object
     */
    @Override
    public Customer getCustomerById(Integer customerId) {
        Customer customer;
        customer = customerRepository.findById(customerId).orElse(null);
        return customer;
    }

    /**
     * @param customer 
     */
    @Override
    public void saveOrUpdateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    /**
     * @param customer 
     */
    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}
// 198.62
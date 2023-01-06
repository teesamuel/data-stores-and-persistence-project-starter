package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetService petService;

    public Customer saveCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId)
    {
        return customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
    }

    public Customer addPet(Pet pet, Long customerId)
    {
        Customer customer = getCustomerById(customerId);
        customer.setPet(pet);
        customerRepository.save(customer);
        return customer;
    }

    public Customer getCustomerByPetId(Long petId) {
        Pet pet = petService.findPetById(petId);
        return customerRepository.findByPets(pet)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }
}

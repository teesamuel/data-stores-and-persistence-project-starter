package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerUtil {
    public static CustomerDTO transformEntityToCustomerDTO(Customer customer)
    {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Pet> pets = customer.getPets();
        if(pets != null)
        {
            List<Long> petIds = new ArrayList<>();
            for (Pet pet : pets)
            {
                petIds.add(pet.getId());
            }
            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    public static Customer transformCustomerDTOtoEntity(CustomerDTO customerDTO)
    {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public static List<CustomerDTO> transformEntityListToCustomerDTOList(List<Customer> customerList)
    {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList)
        {
            CustomerDTO customerDTO = transformEntityToCustomerDTO(customer);
            List<Pet> pets = customer.getPets();
            if(pets != null)
            {
                List<Long> petIds = new ArrayList<>();
                for (Pet pet : pets)
                {
                    petIds.add(pet.getId());
                }
                customerDTO.setPetIds(petIds);
            }
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}

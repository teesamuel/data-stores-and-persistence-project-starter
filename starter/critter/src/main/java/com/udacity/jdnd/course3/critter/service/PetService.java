package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerService customerService;

    public Pet savePet(Pet pet, Long customerId)
    {
        Customer customer = customerService.getCustomerById(customerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customerService.addPet(pet, customerId);
        return pet;
    }

    public List<Pet> getAllPets()
    {
        return petRepository.findAll();
    }

    public Pet findPetById(Long id)
    {
        return petRepository.findById(id)
                .orElseThrow(()->new PetNotFoundException("Pet Not found"));
    }

    public List<Pet> getPetsByOwnerId(Long ownerId)
    {
        return petRepository.findPetsByCustomerId(ownerId);
    }

    public void deletePet(Long petId)
    {
        Pet pet = findPetById(petId);
        petRepository.delete(pet);
    }

    public Pet updatePet(long petId, long customerId, Pet pet) {
        Pet oldPet = findPetById(petId);
        Boolean isOwnerChanged = false;
        oldPet.setName(pet.getName());
        oldPet.setNotes(pet.getNotes());
        oldPet.setBirthDate(pet.getBirthDate());
        oldPet.setNotes(pet.getNotes());
        oldPet.setType(pet.getType());
        Customer customer = oldPet.getCustomer();
        if(oldPet.getCustomer().getId() != customerId)
        {
            customer = customerService.getCustomerById(customerId);
            oldPet.setCustomer(customer);
            isOwnerChanged = true;
        }
        pet = petRepository.save(oldPet);
        if(isOwnerChanged)
        {
            customer.setPet(pet);
        }
        return pet;
    }
}

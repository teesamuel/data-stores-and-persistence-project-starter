package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.model.Pet;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PetUtil {
    public static Pet transformPetDTOtoEntity(PetDTO petDTO)
    {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }


    public static PetDTO transformEntitytoPetDTO(Pet pet)
    {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    public static List<PetDTO> transformEntityListToPetDTOList(List<Pet> pets)
    {
        List<PetDTO> petDTOS = new ArrayList<>();
        for (Pet pet : pets)
        {
            petDTOS.add(transformEntitytoPetDTO(pet));
        }
        return petDTOS;
    }
}

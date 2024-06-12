/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:42:20
 * @modify date 2024-06-12 21:42:20
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }

    public Pet getPetByPetId(Long petId){
        Optional<Pet> optionalPet = this.petRepository.findById(petId);
        if(optionalPet.isPresent()){
            return optionalPet.get();
        }
        return optionalPet.orElseThrow(() -> new ResourceNotFoundException("Pet with id: "+ petId + " not found"));
    }


    public List<Pet> findAllPets(){
        return this.petRepository.findAll();
    }
}

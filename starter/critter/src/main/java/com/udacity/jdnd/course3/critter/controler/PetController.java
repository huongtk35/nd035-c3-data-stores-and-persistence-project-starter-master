/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:40:48
 * @modify date 2024-06-12 21:40:48
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Owner;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.OwnerService;
import com.udacity.jdnd.course3.critter.service.PetService;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private PetService petService;

    @PostMapping
    @Transactional
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Owner owner = null;
        if (petDTO.getOwnerId() != 0) {
            owner = this.ownerService.getOwnerById(petDTO.getOwnerId());
        }

        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setType(petDTO.getType());
        pet.setNotes(petDTO.getNotes());
        pet.setOwner(owner);
        pet.setBirthDate(petDTO.getBirthDate());

        Pet newPet = petService.savePet(pet);
        if (owner != null) {
            if (owner.getPets() == null) {
                owner.setPets(new ArrayList<>());
            }
            owner.getPets().add(newPet);
            Owner newOwner = ownerService.saveOwner(owner);
        }
        petDTO.setId(newPet.getId());

        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetByPetId(petId);
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getType());
        petDTO.setNotes(pet.getNotes());
        if (pet.getOwner() != null) {
            petDTO.setOwnerId(pet.getOwner().getOwnerId());
        }
        petDTO.setBirthDate(pet.getBirthDate());
        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<PetDTO> petDTOS = new ArrayList<>();
        List<Pet> pets = petService.findAllPets();
        for (Pet pet : pets) {
            PetDTO petDTO = new PetDTO();
            petDTO.setId(pet.getId());
            petDTO.setName(pet.getName());
            petDTO.setType(pet.getType());
            petDTO.setNotes(pet.getNotes());
            if (pet.getOwner() != null) {
                petDTO.setOwnerId(pet.getOwner().getOwnerId());
            }
            petDTO.setBirthDate(pet.getBirthDate());
            petDTOS.add(petDTO);
        }
        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        Owner owner = this.ownerService.getOwnerById(ownerId);
        List<PetDTO> petDTOS = new ArrayList<>();

        if (!getPets().isEmpty()) {
            for (Pet pet : owner.getPets()) {
                PetDTO petDTO = new PetDTO();
                petDTO.setId(pet.getId());
                petDTO.setName(pet.getName());
                petDTO.setType(pet.getType());
                petDTO.setNotes(pet.getNotes());
                if (pet.getOwner() != null) {
                    petDTO.setOwnerId(pet.getOwner().getOwnerId());
                }
                petDTO.setBirthDate(pet.getBirthDate());
                petDTOS.add(petDTO);
            }
        }

        return petDTOS;
    }
}

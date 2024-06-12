/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:42:17
 * @modify date 2024-06-12 21:42:17
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Owner;
import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.repository.OwnerRepository;

@Service
@Transactional
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public Owner getOwnerById(Long id){
        Optional<Owner> optionalOwner = this.ownerRepository.findById(id);
        if(optionalOwner.isPresent()){
            Owner owner = optionalOwner.get();
            return owner;
        }
        return optionalOwner.orElseThrow(() -> new ResourceNotFoundException("Owner with id: "+ id + " not found"));
    }

    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public List<Owner> getAllCustomers() {
        return  ownerRepository.findAll();
    }
}

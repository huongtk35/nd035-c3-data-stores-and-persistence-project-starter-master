/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:42:04
 * @modify date 2024-06-12 21:42:04
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Pet;


@Repository
@Transactional
public interface PetRepository extends JpaRepository<Pet, Long> {
}
/**
 * @author RoseDao
 * @email [huongtk35@gmail.com]
 * @create date 2024-06-12 21:42:02
 * @modify date 2024-06-12 21:42:02
 * @desc [description]
 */
package com.udacity.jdnd.course3.critter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Owner;

@Repository
@Transactional
public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
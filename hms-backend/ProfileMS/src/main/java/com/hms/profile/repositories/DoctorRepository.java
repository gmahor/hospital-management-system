package com.hms.profile.repositories;

import com.hms.profile.dto.DoctorsDropdown;
import com.hms.profile.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    boolean existsByEmail(String email);

    boolean existsByNameAndEmail(String name, String email);

    @Query(nativeQuery = true, name = "SELECT d.id as id , d.name as name From doctors d")
    List<DoctorsDropdown> getDoctorsName();

}

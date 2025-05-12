package com.example.lab3_20210740.Repository;

import com.example.lab3_20210740.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor> findByHospitalId(Integer hospitalId);
}

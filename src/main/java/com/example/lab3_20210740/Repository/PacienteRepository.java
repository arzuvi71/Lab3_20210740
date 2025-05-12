package com.example.lab3_20210740.Repository;

import com.example.lab3_20210740.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    List<Paciente> findByHospitalId(Integer hospitalId);
}
package com.example.lab3_20210740.Repository;

import com.example.lab3_20210740.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.doctor.id = :newDoctorId WHERE p.doctor.id = :oldDoctorId")
    int updateDoctorByOldDoctorId(@Param("oldDoctorId") Integer oldDoctorId, @Param("newDoctorId") Integer newDoctorId);

    List<Paciente> findByHospitalId(Integer hospitalId);
    @Query("SELECT p FROM Paciente p WHERE p.doctor.id = :doctorId AND p.fecha_cita >= CURRENT_DATE")
    List<Paciente> findUpcomingAppointmentsByDoctor(@Param("doctorId") Integer doctorId);



}
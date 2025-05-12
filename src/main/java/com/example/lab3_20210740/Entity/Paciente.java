package com.example.lab3_20210740.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter; // O usar @Data para todo
import lombok.EqualsAndHashCode; // Para implementar equals y hashCode
import lombok.ToString; // Para implementar toString


@Entity
@Table(name = "paciente")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int id;

    @EqualsAndHashCode.Include
    @ToString.Include
    private String nombre;

    @ToString.Include
    private int edad;

    @ToString.Include
    private String genero;

    @ToString.Include
    private String diagnostico;

    @ToString.Include
    private LocalDate fecha_cita;

    @ToString.Include
    private int numero_habitacion;

    @ManyToOne
    @JoinColumn(name="doctor_id",nullable=false)
    @EqualsAndHashCode.Exclude
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="hospital_id",nullable=false)
    @EqualsAndHashCode.Exclude
    private Hospital hospital;
}
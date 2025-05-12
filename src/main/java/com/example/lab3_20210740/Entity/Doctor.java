package com.example.lab3_20210740.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="doctor")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int id;

    @EqualsAndHashCode.Include
    @ToString.Include
    private String nombre;

    @ToString.Include
    private String especialidad;

    @ManyToOne
    @JoinColumn(name="hospital_id",nullable=false)
    @EqualsAndHashCode.Exclude
    private Hospital hospital;


}
package com.example.lab3_20210740.Controller;

import com.example.lab3_20210740.Entity.Doctor;
import com.example.lab3_20210740.Entity.Paciente;
import com.example.lab3_20210740.Repository.DoctorRepository;
import com.example.lab3_20210740.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctores")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = {"", "/"})
    public String listarDoctores(Model model) {
        List<Doctor> listaDoctores = doctorRepository.findAll();
        model.addAttribute("listaDoctores", listaDoctores);
        return "listaDoctores";
    }

    @GetMapping("/{id}/proximas-citas")
    public String mostrarProximasCitas(@PathVariable("id") int doctorId, Model model) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            List<Paciente> proximasCitas = pacienteRepository.findUpcomingAppointmentsByDoctor(doctorId);

            model.addAttribute("doctor", doctor);
            model.addAttribute("proximasCitas", proximasCitas);
            return "proximasCitas";
        } else {
            return "redirect:/doctores";
        }
    }
}
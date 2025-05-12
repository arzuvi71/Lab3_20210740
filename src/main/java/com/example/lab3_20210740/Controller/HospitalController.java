package com.example.lab3_20210740.Controller;

import com.example.lab3_20210740.Entity.Doctor;
import com.example.lab3_20210740.Entity.Hospital;
import com.example.lab3_20210740.Entity.Paciente;
import com.example.lab3_20210740.Repository.DoctorRepository;
import com.example.lab3_20210740.Repository.HospitalRepository;
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
@RequestMapping("/hospitales")
public class HospitalController {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping(value = {"", "/"})
    public String listarHospitales(Model model) {
        List<Hospital> listaHospitales = hospitalRepository.findAll();
        model.addAttribute("listaHospitales", listaHospitales);
        return "listaHospitales";
    }

    @GetMapping("/{id}/doctores")
    public String listarDoctoresPorHospital(@PathVariable("id") int hospitalId, Model model) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);

        if (optionalHospital.isPresent()) {
            Hospital hospital = optionalHospital.get();

            List<Doctor> listaDoctores = doctorRepository.findByHospitalId(hospitalId);

            model.addAttribute("hospital", hospital);
            model.addAttribute("listaDoctores", listaDoctores);
            return "listaDoctoresPorHospital";
        } else {
            return "redirect:/hospitales";
        }
    }

    @GetMapping("/{id}/pacientes")
    public String listarPacientesPorHospital(@PathVariable("id") int hospitalId, Model model) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);

        if (optionalHospital.isPresent()) {
            Hospital hospital = optionalHospital.get();
            List<Paciente> listaPacientes = pacienteRepository.findByHospitalId(hospitalId);

            model.addAttribute("hospital", hospital);
            model.addAttribute("listaPacientes", listaPacientes);
            return "listaPacientesPorHospital";
        } else {
            return "redirect:/hospitales";
        }
    }
}
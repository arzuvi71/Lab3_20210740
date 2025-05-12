package com.example.lab3_20210740.Controller;

import com.example.lab3_20210740.Entity.Doctor;
import com.example.lab3_20210740.Entity.Paciente;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.lab3_20210740.Repository.DoctorRepository;
import com.example.lab3_20210740.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping(value = {"", "/"})
    public String listarPacientes(Model model) {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        model.addAttribute("listaPacientes", listaPacientes);
        return "listaPacientes";
    }

    @GetMapping("/derivar")
    public String mostrarFormularioDerivar(Model model) {
        List<Doctor> listaDoctores = doctorRepository.findAll();
        model.addAttribute("listaDoctores", listaDoctores);

        return "derivarPacientes";
    }

    @PostMapping("/derivar")
    public String derivarPacientes(
            @RequestParam("oldDoctorId") int oldDoctorId,
            @RequestParam("newDoctorId") int newDoctorId,
            RedirectAttributes redirectAttributes) {

        if (oldDoctorId == newDoctorId) {
            redirectAttributes.addFlashAttribute("msgError", "No se puede derivar pacientes al mismo doctor.");
            return "redirect:/pacientes/derivar";
        }

        int numPacientesActualizados = pacienteRepository.updateDoctorByOldDoctorId(oldDoctorId, newDoctorId);

        redirectAttributes.addFlashAttribute("msgSuccess", "Se derivaron " + numPacientesActualizados + " pacientes.");

        return "redirect:/pacientes";
    }
}

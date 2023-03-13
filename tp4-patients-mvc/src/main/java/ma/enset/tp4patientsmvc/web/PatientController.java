package ma.enset.tp4patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.tp4patientsmvc.entities.Patient;
import ma.enset.tp4patientsmvc.repositories.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String patients(Model model) {
        List<Patient> patients =patientRepository.findAll();
        model.addAttribute("listPatients",patients);
        return "patients";
    }

}

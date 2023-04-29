package ma.enset.patientmvcjpa;

import ma.enset.patientmvcjpa.entities.Patient;
import ma.enset.patientmvcjpa.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientMvcJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(
                    new Patient(null, "chaimae", new Date(), false, 123));
            patientRepository.save(
                    new Patient(null, "fifi", new Date(), false, 110));
            patientRepository.save(
                    new Patient(null, "lolo", new Date(), true, 123));
            patientRepository.save(
                    new Patient(null, "nour", new Date(), false, 100));

            List<Patient> patientList = patientRepository.findAll();
            patientList.forEach(
                    patient -> {
                        System.out.println("=================================");
                        System.out.println(patient.getId());
                        System.out.println(patient.getNom());
                        System.out.println(patient.getScore());
                        System.out.println(patient.getDateNaissance());
                        System.out.println(patient.getMalade());
                    }
            );
            Patient patient = patientRepository.findById(1L).orElse(null);
            System.out.println("==========consulter un patient==================");
            if (patient != null) {
                System.out.println(patient.getId());
                System.out.println(patient.getNom());
                System.out.println(patient.getScore());
                System.out.println(patient.getDateNaissance());
                System.out.println(patient.getMalade());
            }
            patient.setScore(258);
            patientRepository.save(patient);
            System.out.println("les informations du patients 1 que nous avons mis à jour ");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getScore());
            System.out.println(patient.getDateNaissance());
            System.out.println(patient.getMalade());
            patientRepository.deleteById(1L);
        };

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


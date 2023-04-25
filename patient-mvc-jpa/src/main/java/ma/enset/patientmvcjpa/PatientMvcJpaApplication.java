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

@SpringBootApplication
public class PatientMvcJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcJpaApplication.class, args);
    }

    //Pour tester notre application on utilise commandLineRunner
    //On ajoute qq patients
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
                    new Patient(null, "blabla", new Date(), false, 100));
            patientRepository.findAll().forEach(
                    patient -> {
                        System.out.println(patient.getNom());
                    }
            );
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


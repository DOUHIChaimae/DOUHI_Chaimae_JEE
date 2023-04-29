package ma.enset.hospital;

import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 100; i++) {
            //patientRepository.save(new Patient(null, "douhi", new Date(), Math.random() > 0.5, (int) (Math.random() * 100)));
        }
        System.out.println("Afficher la liste des patients");
        List<Patient> patientList = patientRepository.findAll();
        patientList.forEach(
                patient -> {
                    System.out.println("=================================");
                    System.out.println(patient.getId());
                    System.out.println(patient.getName());
                    System.out.println(patient.getScore());
                    System.out.println(patient.getBirthday());
                    System.out.println(patient.getIsSick());
                }
        );
        Patient patient = patientRepository.findById(1L).orElse(null);
        System.out.println("=============consulter un patient==================");
        if (patient != null) {
            System.out.println(patient.getId());
            System.out.println(patient.getName());
            System.out.println(patient.getScore());
            System.out.println(patient.getBirthday());
            System.out.println(patient.getIsSick());
        }
        patient.setScore(258);
        patientRepository.save(patient);
        System.out.println("les informations du patients 1 que nous avons mis à jour ");
        System.out.println(patient.getId());
        System.out.println(patient.getName());
        System.out.println(patient.getScore());
        System.out.println(patient.getBirthday());
        System.out.println(patient.getIsSick());
        patientRepository.deleteById(1L);

        System.out.println("Afficher les patients qui sont malades");
        List<Patient> bySickness = patientRepository.findByIsSick(true);
        bySickness.forEach(
                p -> {
                    System.out.println("======================================");
                    System.out.println(p.getId());
                    System.out.println(p.getName());
                    System.out.println(p.getBirthday());
                    System.out.println(p.getScore());
                    System.out.println(p.getIsSick());
                }
        );

        System.out.println("Page des patients malades");
        Page<Patient> bySicknessPage = patientRepository.findByIsSick(true, PageRequest.of(0, 5));
        bySicknessPage.forEach(
                p -> {
                    System.out.println("======================================");
                    System.out.println(p.getId());
                    System.out.println(p.getName());
                    System.out.println(p.getBirthday());
                    System.out.println(p.getScore());
                    System.out.println(p.getIsSick());
                }
        );
        System.out.println("chercher des patients par nom qui contient 'ou' et qui ont le score inférieur à 40");
        List<Patient> patientsByNameAndScore = patientRepository.searchPatients("%ou%", 40);
        patientsByNameAndScore.forEach(
                p -> {
                    System.out.println("======================================");
                    System.out.println(p.getId());
                    System.out.println(p.getName());
                    System.out.println(p.getBirthday());
                    System.out.println(p.getScore());
                    System.out.println(p.getIsSick());
                }
        );

        System.out.println("retourner seulement les premiers 5 patients");
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(1, 5));
        System.out.println("total pages : " + patients.getTotalPages());
        System.out.println(patients.getTotalElements());
        System.out.println(patients.getNumber());
        List<Patient> content = patients.getContent();

    }
}

package ma.enset.hospital.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Transactional
public class BootstrapDataServiceImpl implements BootstrapDataService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private IHospitalService hospitalService;
    private UserService userService;

    @Override
    public void initPatients() {
        Stream.of("patient1", "patient2", "patient3", "patient4", "patient5")
                .forEach(name -> {
                    Patient patient = new Patient();
                    patient.setName(name);
                    patient.setBirthday(new Date());
                    patient.setIsSick(false);
                    patient.setScore(100);
                    hospitalService.savePatient(patient);
                });
    }

    @Override
    public void initDoctors() {
        Stream.of("medecin1", "medecin2", "medecin3", "medecin4")
                .forEach(name -> {
                            Medecin medecin = new Medecin();
                            medecin.setName(name);
                            medecin.setSpeciality(Math.random() > 0.5 ? "Cardio" : "dentist");
                            medecin.setEmail(name + "@gmail.com");
                            hospitalService.saveMedecin(medecin);
                        }
                );
    }

    @Override
    public void initRoles() {
        Stream.of("STUDENT", "USER", "ADMIN")
                .forEach(role -> {
                    Role role1 = new Role();
                    role1.setRoleName(role);
                    userService.addNewRole(role1);
                });
    }

    @Override
    public void initAppointments() {
        Patient patient = patientRepository.findAll().get(0);
        Medecin medecin = medecinRepository.findByName("medecin2");
        RendezVous rendezVous = new RendezVous();
        rendezVous.setDate(new Date());
        rendezVous.setStatus(RDVStatus.PENDING);
        rendezVous.setMedecin(medecin);
        rendezVous.setPatient(patient);
        hospitalService.saveRDV(rendezVous);
    }

    @Override
    public void initConsultations() {
        RendezVous rendezVous = rendezVousRepository.findAll().get(0);
        Consultation consultation = new Consultation();
        consultation.setConsultationDate(new Date());
        consultation.setRendezVous(rendezVous);
        consultation.setRapport("Rapport de la consultation..");
        hospitalService.saveConsultation(consultation);
    }

    @Override
    public void initUsers() {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("user123");
        userService.addNewUser(user1);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword("admin123");
        userService.addNewUser(user2);
    }

    @Override
    public void addSomeRolesToUsers() {
        userService.addRoleToUser("user1", "STUDENT");
        userService.addRoleToUser("user1", "USER");
        userService.addRoleToUser("admin", "USER");
        userService.addRoleToUser("admin", "ADMIN");
    }

    @Override
    public void initAuthentication() {
        try {
            User user = userService.authenticate("user1", "user123");
            System.out.println("Id : " + user.getUserId());
            System.out.println("Username : " + user.getUsername());
            user.getRoles().forEach(role -> System.out.println("Role : " + role.toString()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}

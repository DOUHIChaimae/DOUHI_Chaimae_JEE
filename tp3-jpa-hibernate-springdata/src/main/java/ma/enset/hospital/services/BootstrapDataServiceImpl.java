package ma.enset.hospital.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.DoctorRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Transactional
public class BootstrapDataServiceImpl implements BootstrapDataService {
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private IHospitalService hospitalService;
    private UserService userService;

    @Override
    public void initPatients() {
        Stream.of("patient1", "patient2", "patient3")
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
        Stream.of("doctor1", "doctor2", "doctor3", "doctor4")
                .forEach(name -> {
                            Doctor doctor = new Doctor();
                            doctor.setName(name);
                            doctor.setSpeciality(Math.random() > 0.5 ? "Cardio" : "dentist");
                            doctor.setEmail(name + "@gmail.com");
                            hospitalService.saveMedecin(doctor);
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
        Doctor doctor = doctorRepository.findByName("doctor2");
        Appointment appointment = new Appointment();
        appointment.setDate(new Date());
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        hospitalService.saveRDV(appointment);
    }

    @Override
    public void initConsultations() {
        Appointment appointment = appointmentRepository.findAll().get(0);
        Consultation consultation = new Consultation();
        consultation.setConsultationDate(new Date());
        consultation.setAppointment(appointment);
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

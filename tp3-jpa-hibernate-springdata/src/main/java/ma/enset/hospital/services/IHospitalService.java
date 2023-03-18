package ma.enset.hospital.services;

import ma.enset.hospital.entities.Consultation;
import ma.enset.hospital.entities.Doctor;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.entities.Appointment;

public interface IHospitalService {
     Patient savePatient(Patient patient);
     Doctor saveMedecin(Doctor doctor);
     Appointment saveRDV(Appointment appointment);
     Consultation saveConsultation(Consultation consultation);
}

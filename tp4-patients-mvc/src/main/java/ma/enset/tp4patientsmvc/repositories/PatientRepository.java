package ma.enset.tp4patientsmvc.repositories;

import ma.enset.tp4patientsmvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}

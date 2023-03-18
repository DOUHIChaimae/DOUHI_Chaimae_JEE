package ma.enset.hospital.repositories;

import ma.enset.hospital.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByName(String name);

}

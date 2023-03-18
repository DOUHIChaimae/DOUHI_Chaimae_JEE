package ma.enset.hospital.repositories;

import ma.enset.hospital.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

package ma.enset.conferencemanagementspringangular.repositories;

import ma.enset.conferencemanagementspringangular.entities.Participant;
import ma.enset.conferencemanagementspringangular.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle,Long> {
}

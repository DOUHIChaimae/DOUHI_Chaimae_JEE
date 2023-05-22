package ma.enset.conferencemanagementspringangular.repositories;

import ma.enset.conferencemanagementspringangular.entities.Inscription;
import ma.enset.conferencemanagementspringangular.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
}

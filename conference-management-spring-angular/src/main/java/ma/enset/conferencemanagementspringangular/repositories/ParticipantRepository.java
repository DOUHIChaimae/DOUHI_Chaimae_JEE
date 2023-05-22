package ma.enset.conferencemanagementspringangular.repositories;

import ma.enset.conferencemanagementspringangular.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
}

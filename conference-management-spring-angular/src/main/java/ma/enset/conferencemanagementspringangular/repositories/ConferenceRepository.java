package ma.enset.conferencemanagementspringangular.repositories;

import ma.enset.conferencemanagementspringangular.entities.Conference;
import ma.enset.conferencemanagementspringangular.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference,Long> {
}

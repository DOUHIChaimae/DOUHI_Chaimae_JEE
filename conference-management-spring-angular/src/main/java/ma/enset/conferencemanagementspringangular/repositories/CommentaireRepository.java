package ma.enset.conferencemanagementspringangular.repositories;

import ma.enset.conferencemanagementspringangular.entities.Commentaire;
import ma.enset.conferencemanagementspringangular.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
}

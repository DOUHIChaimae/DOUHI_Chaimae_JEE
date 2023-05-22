package ma.enset.conferencemanagementspringangular.repositories;


import ma.enset.conferencemanagementspringangular.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session,Long> {
    @Query("select s from Session s where s.nom like :kw")
    List<Session> searchSession(@Param("kw") String keyword);
}

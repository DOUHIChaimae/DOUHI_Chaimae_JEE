package ma.enset.conferencemanagementspringangular.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MO")
public class Moderateur extends Participant {
    private String specialite;
    @OneToOne(mappedBy = "moderateur")
    private Session session;

}

package ma.enset.conferencemanagementspringangular.entities;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@DiscriminatorValue("SP")
public class Speaker extends Participant{
    private String profil;
    @OneToMany(mappedBy = "speaker")
    private List<Conference> conferences;
}

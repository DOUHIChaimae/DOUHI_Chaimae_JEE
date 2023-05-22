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
@DiscriminatorValue("INV")
public class Invite extends Participant {
    private String affiliation;
    @OneToMany(mappedBy = "invite")
    private List<Inscription> inscriptions;

}

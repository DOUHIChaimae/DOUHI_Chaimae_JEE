package ma.enset.conferencemanagementspringangular.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "session")
    private List<Conference> conferences;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Moderateur moderateur;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Salle salle;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Inscription inscription;
}

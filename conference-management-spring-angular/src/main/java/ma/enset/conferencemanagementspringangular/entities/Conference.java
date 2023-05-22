package ma.enset.conferencemanagementspringangular.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private Date date;
    private Date heureDebut;
    private Date heureFin;
    private String description;

    @ManyToOne
    private Speaker speaker;

    @ManyToOne
    private Session session;

    @OneToMany(mappedBy = "conference")
    private List<Commentaire> commentaires;
}

package ma.enset.conferencemanagementspringangular.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String contenu;
    private int likes;

    @ManyToOne
    private Conference conference;

    @ManyToOne
    private Participant participant;
}

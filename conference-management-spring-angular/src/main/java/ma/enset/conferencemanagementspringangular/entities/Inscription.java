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
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double prix;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "inscription")
    private Session session;

    @ManyToOne
    private Invite invite;
}

package ma.enset.conferencemanagementspringangular;

import ma.enset.conferencemanagementspringangular.entities.*;
import ma.enset.conferencemanagementspringangular.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ConferenceManagementSpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceManagementSpringAngularApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ParticipantRepository participantRepository,
                            CommentaireRepository commentaireRepository,
                            SalleRepository salleRepository,
                            SessionRepository sessionRepository,
                            InscriptionRepository inscriptionRepository,
                            ConferenceRepository conferenceRepository) {
        return args -> {
            Stream.of("Chaimae", "Amina", "Azzedine", "Abdelkader").forEach(name -> {
                Speaker speaker = new Speaker();
                speaker.setEmail(name + "Douhi@gmail.com");
                speaker.setProfil("profil.ma");
                speaker.setPhoto("photo1");
                speaker.setGenre(Gender.FEMININ);
                participantRepository.save(speaker);

                Invite invite = new Invite();
                invite.setEmail(name + "Douhi@gmail.com");
                invite.setAffiliation("afiiliation1");
                invite.setPhoto("photo1");
                invite.setGenre(Gender.MASCULAIN);
                participantRepository.save(invite);

                Moderateur moderateur = new Moderateur();
                moderateur.setEmail(name + "Douhi@gmail.com");
                moderateur.setSpecialite("specialité845");
                moderateur.setPhoto("photo1");
                moderateur.setGenre(Gender.MASCULAIN);
                participantRepository.save(moderateur);

            });
            participantRepository.findAll().forEach(
                    participant -> {
                        Commentaire commentaire = new Commentaire();
                        commentaire.setDate(new Date());
                        commentaire.setLikes(12300);
                        commentaire.setParticipant(participant);
                        commentaire.setContenu("content1155454");
                        commentaireRepository.save(commentaire);
                    }
            );
            for (int i = 0; i < 27; i++) {
                Salle salle = new Salle();
                if (Math.random() > 0.5) {
                    salle.setNom("salle info");
                } else salle.setNom("salle buiologie");
                salleRepository.save(salle);
            }

            Participant participant = new Participant();
            participant.setNom("participant2322");
            participant.setGenre(Gender.FEMININ);
            participant.setEmail("test121222");
            participant.setPhoto("testPhoto222222");
            participantRepository.save(participant);
            Participant participant2 = new Participant();
            participant.setNom("participant2322");
            participant.setGenre(Gender.FEMININ);
            participant.setEmail("test121222");
            participant.setPhoto("testPhoto222222");
            participantRepository.save(participant2);
            List<Commentaire> commentaires = commentaireRepository.findAll();
            for (Commentaire commentaire : commentaires) {
                commentaire.setParticipant(participant2);
                commentaireRepository.save(commentaire);
            }

            participantRepository.findAll().forEach(participant1 -> {
                if (participant1 instanceof Speaker) {
                    Speaker speaker = (Speaker) participant1;
                    Conference conference = new Conference();
                    conference.setHeureDebut(new Date());
                    conference.setHeureFin(new Date());
                    conference.setDescription("this is a description");
                    conference.setCommentaires(commentaireRepository.findAll());
                    conference.setSpeaker(speaker);
                    conferenceRepository.save(conference);
                }
            });

            salleRepository.findAll().forEach(salle -> {
                sessionRepository.findAll().forEach(s -> {
                    s.setSalle(salle);
                    sessionRepository.save(s);
                });
            });

            for (int i =0;i<19;i++){

                inscriptionRepository.findAll().forEach(inscription -> {
                    sessionRepository.findAll().forEach(session1 -> {
                        session1.setInscription(inscription);
                        sessionRepository.save(session1);
                    });
                });
            }
            conferenceRepository.findAll().forEach(conference -> {
                sessionRepository.findAll().forEach(session -> {
                    conference.setSession(session);
                    conference.setTitre("conference");
                conferenceRepository.save(conference);
                });
            });

        };
    }

}


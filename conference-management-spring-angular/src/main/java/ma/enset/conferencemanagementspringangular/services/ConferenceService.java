package ma.enset.conferencemanagementspringangular.services;

import ma.enset.conferencemanagementspringangular.dtos.ConferenceDto;
import ma.enset.conferencemanagementspringangular.dtos.SessionDto;
import ma.enset.conferencemanagementspringangular.exceptions.ConferenceNotFoundException;

import java.util.List;

public interface ConferenceService {

    SessionDto saveConference(ConferenceDto conferenceDto);

    List<ConferenceDto> listConferences();

    ConferenceDto getConference(Long conferenceId) throws ConferenceNotFoundException;


    ConferenceDto updateConference(ConferenceDto conferenceDto);

    void deleteConference(Long conferenceId);

    List<ConferenceDto> searchConference(String keyword);

}

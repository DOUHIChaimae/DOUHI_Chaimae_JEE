package ma.enset.conferencemanagementspringangular.services;

import ma.enset.conferencemanagementspringangular.dtos.ConferenceDto;
import ma.enset.conferencemanagementspringangular.dtos.SessionDto;
import ma.enset.conferencemanagementspringangular.exceptions.ConferenceNotFoundException;

import java.util.List;

public class ConferenceServiceImpl implements ConferenceService{
    @Override
    public SessionDto saveConference(ConferenceDto conferenceDto) {
        return null;
    }

    @Override
    public List<ConferenceDto> listConferences() {
        return null;
    }

    @Override
    public ConferenceDto getConference(Long conferenceId) throws ConferenceNotFoundException {
        return null;
    }

    @Override
    public ConferenceDto updateConference(ConferenceDto conferenceDto) {
        return null;
    }

    @Override
    public void deleteConference(Long conferenceId) {

    }

    @Override
    public List<ConferenceDto> searchConference(String keyword) {
        return null;
    }
}

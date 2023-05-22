package ma.enset.conferencemanagementspringangular.services;

import ma.enset.conferencemanagementspringangular.dtos.SessionDto;
import ma.enset.conferencemanagementspringangular.exceptions.SessionNotFoundException;

import java.util.List;

public interface SessionService {

    SessionDto saveSession(SessionDto sessionDto);

    List<SessionDto> listSessions();

    SessionDto getSession(Long sessionId) throws SessionNotFoundException;


    SessionDto updateSession(SessionDto sessionDto);

    void deleteSession(Long sessionId);

    List<SessionDto> searchSessionDto(String keyword);

}

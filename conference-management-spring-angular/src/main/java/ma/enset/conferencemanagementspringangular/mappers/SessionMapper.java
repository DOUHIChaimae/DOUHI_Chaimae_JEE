package ma.enset.conferencemanagementspringangular.mappers;

import ma.enset.conferencemanagementspringangular.dtos.SessionDto;
import ma.enset.conferencemanagementspringangular.entities.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SessionMapper {
    public SessionDto fromSession(Session session) {
        SessionDto sessionDto = new SessionDto();
        BeanUtils.copyProperties(session, sessionDto);
        return sessionDto;
    }

    public Session fromSessionDTO(SessionDto sessionDto) {
        Session session = new Session();
        BeanUtils.copyProperties(sessionDto, session);
        return session;
    }

}

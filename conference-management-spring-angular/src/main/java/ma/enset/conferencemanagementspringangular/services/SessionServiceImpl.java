package ma.enset.conferencemanagementspringangular.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.conferencemanagementspringangular.dtos.SessionDto;
import ma.enset.conferencemanagementspringangular.entities.Session;
import ma.enset.conferencemanagementspringangular.exceptions.SessionNotFoundException;
import ma.enset.conferencemanagementspringangular.mappers.SessionMapper;
import ma.enset.conferencemanagementspringangular.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SessionServiceImpl implements SessionService {
    private SessionMapper dtoMapper;
    private SessionRepository sessionRepository;

    @Override
    public SessionDto saveSession(SessionDto sessionDto) {
        log.info("Saving new Session");
        Session session = dtoMapper.fromSessionDTO(sessionDto);
        Session savedSession = sessionRepository.save(session);
        return dtoMapper.fromSession(savedSession);
    }

    @Override
    public List<SessionDto> listSessions() {
        List<Session> sessions = sessionRepository.findAll();
        List<SessionDto> sessionDtos = sessions.stream()
                .map(customer -> dtoMapper.fromSession(customer))
                .collect(Collectors.toList());
        return sessionDtos;
    }

    @Override
    public SessionDto getSession(Long sessionId) throws SessionNotFoundException {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session Not found"));
        return dtoMapper.fromSession(session);
    }

    @Override
    public SessionDto updateSession(SessionDto sessionDto) {
        log.info("Saving new Customer");
        Session session = dtoMapper.fromSessionDTO(sessionDto);
        Session savedCustomer = sessionRepository.save(session);
        return dtoMapper.fromSession(savedCustomer);
    }

    @Override
    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);

    }

    @Override
    public List<SessionDto> searchSessionDto(String keyword) {
        List<Session> sessions = sessionRepository.searchSession(keyword);
        List<SessionDto> sessionDtos = sessions.stream().map(session -> dtoMapper.fromSession(session)).collect(Collectors.toList());
        return sessionDtos;
    }
}

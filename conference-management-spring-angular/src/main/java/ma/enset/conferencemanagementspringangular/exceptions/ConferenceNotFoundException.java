package ma.enset.conferencemanagementspringangular.exceptions;

public class ConferenceNotFoundException extends Exception {
    ConferenceNotFoundException(String message){
        super(message);
    }
}

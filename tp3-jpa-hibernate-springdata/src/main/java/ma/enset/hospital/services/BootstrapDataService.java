package ma.enset.hospital.services;

public interface BootstrapDataService {
    void initPatients();
    void initDoctors();
    void initRoles();
    void initAppointments();
    void initConsultations();
    void initUsers();
    void addSomeRolesToUsers();
    void initAuthentication();

}

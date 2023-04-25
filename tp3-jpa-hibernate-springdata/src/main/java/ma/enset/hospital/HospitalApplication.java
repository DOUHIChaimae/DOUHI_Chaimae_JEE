package ma.enset.hospital;

import ma.enset.hospital.services.BootstrapDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalApplication {
    @Autowired
    private BootstrapDataService bootstrapDataService;

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start() {
        return args -> {
            this.bootstrapDataService.initPatients();
            this.bootstrapDataService.initDoctors();
            this.bootstrapDataService.initAppointments();
            this.bootstrapDataService.initConsultations();
            this.bootstrapDataService.initUsers();
            this.bootstrapDataService.initRoles();
            this.bootstrapDataService.addSomeRolesToUsers();
            this.bootstrapDataService.initAuthentication();
        };
    }
}

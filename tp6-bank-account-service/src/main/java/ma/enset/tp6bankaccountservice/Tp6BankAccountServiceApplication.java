package ma.enset.tp6bankaccountservice;

import ma.enset.tp6bankaccountservice.entities.BankAccount;
import ma.enset.tp6bankaccountservice.enums.AccountType;
import ma.enset.tp6bankaccountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class Tp6BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp6BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                BankAccount bankAccount = BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .balance(10000 + Math.random() * 900000)
                        .currency("MAD")
                        .createdAt(new Date())
                        .build();
                bankAccountRepository.save(bankAccount);

            }
        };
    }

}

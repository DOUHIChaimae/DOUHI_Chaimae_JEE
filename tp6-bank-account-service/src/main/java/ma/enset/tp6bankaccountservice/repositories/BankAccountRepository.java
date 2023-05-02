package ma.enset.tp6bankaccountservice.repositories;

import ma.enset.tp6bankaccountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}

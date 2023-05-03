package ma.enset.tp6bankaccountservice.repositories;

import ma.enset.tp6bankaccountservice.entities.BankAccount;
import ma.enset.tp6bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
       @RestResource(path = "/byType")
        List<BankAccount> findByType(@Param("t") AccountType type);

        //if we use the RestController, we need to add a method that invoke this method
}

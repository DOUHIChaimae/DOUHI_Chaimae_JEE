package ma.enset.tp6bankaccountservice.entities;

import ma.enset.tp6bankaccountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class, name = "p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();

}

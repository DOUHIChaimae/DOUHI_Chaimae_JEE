package ma.enset.tp6bankaccountservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.tp6bankaccountservice.enums.AccountType;

import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccountResponseDto {
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    private AccountType type;
}

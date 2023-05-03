package ma.enset.tp6bankaccountservice.services;

import ma.enset.tp6bankaccountservice.dtos.BankAccountRequestDTO;
import ma.enset.tp6bankaccountservice.dtos.BankAccountResponseDto;

public interface AccountService {
    /**
     *Adding an account is not just adding it to the database but checking business rules :)
     */

    public BankAccountResponseDto addAccount(BankAccountRequestDTO bankAccountDTO);
    public BankAccountResponseDto updateAccount(String id,BankAccountRequestDTO bankAccountDTO);
}

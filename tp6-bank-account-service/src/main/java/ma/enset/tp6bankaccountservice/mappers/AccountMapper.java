package ma.enset.tp6bankaccountservice.mappers;

import ma.enset.tp6bankaccountservice.dtos.BankAccountRequestDTO;
import ma.enset.tp6bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.tp6bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDto fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDto bankAccountResponseDto = new BankAccountResponseDto();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDto);
        return bankAccountResponseDto;
    }
    public BankAccount fromBankAccountDTO(BankAccountResponseDto bankAccountResponseDto){
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountResponseDto, bankAccount);
        return bankAccount;
    }
}

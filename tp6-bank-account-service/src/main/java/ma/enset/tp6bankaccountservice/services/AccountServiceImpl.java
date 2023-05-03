package ma.enset.tp6bankaccountservice.services;

import ma.enset.tp6bankaccountservice.dtos.BankAccountRequestDTO;
import ma.enset.tp6bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.tp6bankaccountservice.entities.BankAccount;
import ma.enset.tp6bankaccountservice.mappers.AccountMapper;
import ma.enset.tp6bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * Mapping =>
 * <p>
 * BankAccount bankAccountEntity = BankAccount.builder()
 * .id(UUID.randomUUID().toString()) //mapping
 * .createdAt(new Date())
 * .balance(bankAccountRequestDto.getBalance())
 * .currency(bankAccountRequestDto.getCurrency())
 * .type(bankAccountRequestDto.getType())
 * .build();
 * <p>
 * BankAccountResponseDto bankAccountResponseDto1 = new BankAccountResponseDto().builder()
 * .id(savedBankAccountEntity.getId())
 * .type(savedBankAccountEntity.getType())
 * .createdAt(savedBankAccountEntity.getCreatedAt())
 * .balance(savedBankAccountEntity.getBalance())
 * .currency(savedBankAccountEntity.getCurrency())
 * .build();
 */

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDto addAccount(BankAccountRequestDTO bankAccountRequestDto) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString()) //mapping
                .createdAt(new Date())
                .balance(bankAccountRequestDto.getBalance())
                .currency(bankAccountRequestDto.getCurrency())
                .type(bankAccountRequestDto.getType())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDto bankAccountResponseDto = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDto;
    }

    @Override
    public BankAccountResponseDto updateAccount(String id, BankAccountRequestDTO bankAccountRequestDto) {
        BankAccount bankAccount = BankAccount.builder()
                .id(id)
                .createdAt(new Date())
                .balance(bankAccountRequestDto.getBalance())
                .currency(bankAccountRequestDto.getCurrency())
                .type(bankAccountRequestDto.getType())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDto bankAccountResponseDto = accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountResponseDto;
    }
}

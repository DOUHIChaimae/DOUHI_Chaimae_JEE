package ma.enset.tp6bankaccountservice.web;

import ma.enset.tp6bankaccountservice.dtos.BankAccountRequestDTO;
import ma.enset.tp6bankaccountservice.dtos.BankAccountResponseDto;
import ma.enset.tp6bankaccountservice.entities.BankAccount;
import ma.enset.tp6bankaccountservice.mappers.AccountMapper;
import ma.enset.tp6bankaccountservice.repositories.BankAccountRepository;
import ma.enset.tp6bankaccountservice.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }


    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
    }

    /*@PostMapping("/bankAccounts")
        public BankAccount save(@RequestBody BankAccount bankAccount) {
            if(bankAccount.getId() == null) bankAccount.setId(UUID.randomUUID().toString());
            return bankAccountRepository.save(bankAccount);
    }*/

    @PostMapping("/bankAccounts")
    public BankAccountResponseDto save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance() != null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt() != null) account.setCreatedAt(new Date());
        if (bankAccount.getCurrency() != null) account.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getType() != null) account.setType(bankAccount.getType());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}

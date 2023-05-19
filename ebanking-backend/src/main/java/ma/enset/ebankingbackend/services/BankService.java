package ma.enset.ebankingbackend.services;

import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    public BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("17cb6654-b279-43df-a76c-d2743a176700").orElse(null);
        if (bankAccount != null) {
            System.out.println("the account information");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getClass().getSimpleName());

            if (bankAccount instanceof CurrentAccount) {
                System.out.println("overDraft : " + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("interestRate : " + ((SavingAccount) bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(operation -> {
                System.out.println("Information of operation ");
                System.out.println(operation.getType() + "\t" + operation.getAmount() + "\t" + operation.getOperationDate());
            });
        }
    }
}

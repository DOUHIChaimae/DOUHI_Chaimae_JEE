package ma.enset.ebankingbackend.mappers;

import ma.enset.ebankingbackend.dtos.*;
import ma.enset.ebankingbackend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public final class BankAccountMapper {

    public static CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        if(Objects.nonNull(customer.getBankAccounts()))
            customerDTO.setAccounts(fromBankAccounts(customer.getBankAccounts()));
        return customerDTO;
    }

    private static List<BankAccountDTO> fromBankAccounts(List<BankAccount> bankAccounts) {
        return bankAccounts.stream()
                .map(account -> fromBankAccountEntity(account, false))
                .collect(Collectors.toList());
    }

    public static Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public static BankAccountDTO fromBankAccountEntity(BankAccount bankAccount, boolean mapperCustomer) {
        BankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(bankAccount, savingBankAccountDTO);
        if (mapperCustomer)
            savingBankAccountDTO.setCustomerDTO(fromCustomer(bankAccount.getCustomer()));
        savingBankAccountDTO.setType(bankAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public static BankAccountDTO fromBankAccountEntity(BankAccount bankAccount) {
        return fromBankAccountEntity(bankAccount, true);
    }

    public static SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public static CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public static AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }

}

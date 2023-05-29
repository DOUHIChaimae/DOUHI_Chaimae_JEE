package ma.enset.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.BankAccountDTO;
import ma.enset.ebankingbackend.dtos.CustomerDTO;
import ma.enset.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbackend.services.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> customers() {
        return ResponseEntity.ok(bankAccountService.listCustomers());
    }

    //Search
    @GetMapping("/customers/search")
    public ResponseEntity<List<CustomerDTO>> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return ResponseEntity.ok(bankAccountService.searchCustomers("%" + keyword + "%"));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return ResponseEntity.ok(this.bankAccountService.getCustomer(customerId));
    }

    //Save
    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCustomer(customerDTO);
    }

    //Update
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return ResponseEntity.ok(bankAccountService.updateCustomer(customerDTO));
    }

    //Delete
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
        return ResponseEntity.ok(id);
    }

    //get bank accounts of costumer
    @GetMapping("/customers/{id}/accounts")
    public ResponseEntity<List<BankAccountDTO>> banksOfCustomer(@PathVariable(name = "id") Long customerid) {
        return ResponseEntity.ok(bankAccountService.listBankAccountsOfCustomer(customerid));
    }
}

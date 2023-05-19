package ma.enset.ebankingbackend.entities;

import jakarta.persistence.*;
import ma.enset.ebankingbackend.enums.OperationType;

import java.util.Date;
@Entity
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private BankAccount bankAccount;
}

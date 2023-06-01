package ma.enset.billingservice.entities;

import jakarta.persistence.*;
import ma.enset.billingservice.model.Customer;

import java.util.Collection;
import java.util.Date;
@Entity

public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Date billingDate;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    private long customerID;
    @Transient
    private Customer customer;
}

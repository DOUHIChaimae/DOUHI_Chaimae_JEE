package ma.enset.billingservice.entities;

import jakarta.persistence.*;
import ma.enset.billingservice.model.Product;

@Entity
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private double price;
    private  long productID;
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;
}

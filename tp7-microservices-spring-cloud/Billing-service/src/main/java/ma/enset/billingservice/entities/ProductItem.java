package ma.enset.billingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.billingservice.model.Product;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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

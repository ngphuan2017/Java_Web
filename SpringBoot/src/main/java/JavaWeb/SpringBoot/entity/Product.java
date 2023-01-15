package JavaWeb.SpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "PRODUCTID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(name = "PRODUCTNAME")
    private String productname;
    @Column(name = "UNITPRICE")
    private String price;
    @Column(name = "QUANTITY")
    private String quantity;
    @Column(name = "PRODUCTIMAGE")
    private String productImg;
    @Column(name = "DISCONTINUED")
    private String discontinued;
    @Column(name = "UNIT_SALE")
    private String unitSale;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}

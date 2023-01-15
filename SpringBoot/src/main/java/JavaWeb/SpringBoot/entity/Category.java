package JavaWeb.SpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CATEGORIES")
public class Category {
    @Id
    @Column(name = "CATEGORYID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "CATEGORYNAME")
    private String categoryName;
    @Column(name = "CATEGORYIMAGE")
    private String categoryImg;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> productList;
}

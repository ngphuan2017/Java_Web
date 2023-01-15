package JavaWeb.SpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ADDRESSES")
public class Address {
    @Id
    @Column(name = "ADDRESSID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "DISTRICT")
    private String district;
    @Column(name = "STREET")
    private String street;
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Cart cart;
    @ManyToMany
    @JoinTable(name = "USER_ADDRESS",
            joinColumns = @JoinColumn(name = "ADDRESS_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> user;
}

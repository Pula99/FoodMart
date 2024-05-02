package com.cartservice.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table (name = "cart")
public class Cart {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cartNumber;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.MERGE)
    private List<CartItem> cartItems;

}

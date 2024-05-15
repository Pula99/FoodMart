package com.cartservice.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    private Integer productId;

    private Integer quantity;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart")
    @JsonBackReference
    private Cart cart;
}

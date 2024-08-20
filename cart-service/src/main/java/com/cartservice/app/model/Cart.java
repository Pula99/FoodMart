package com.cartservice.app.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table (name = "cart")
public class Cart {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String  id;

    @Enumerated(EnumType.STRING)
    private ConfirmationStatus confirmationStatus;

    private String userId;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

}

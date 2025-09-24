package org.example.backend.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
@Entity
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name", unique = true)
    @NotBlank
    private String itemName;

    @Column(name = "stock")
    @NotBlank
    private int stock;

    @Column(name = "price")
    @NotBlank
    private double price;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private ItemType itemType;
}

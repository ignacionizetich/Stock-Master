package org.example.backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bills")
@Getter
@Setter
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill_number")
    private String billNumber;

    @ManyToOne
    @JoinColumn(name = "id_bill_type", referencedColumnName = "id")
    private BillType billType;

    @Column(name = "date")
    private String date;

    @OneToMany
    @JoinColumn(name = "id_bill", referencedColumnName = "id")
    private List<Purchase> purchases;
}

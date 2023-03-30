package ru.kildeev.marketApplication.core.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username_id")
    private String username;

    @Column(name = "product_id")
    private String product;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "total_price")
    private BigDecimal totalPrice;


}

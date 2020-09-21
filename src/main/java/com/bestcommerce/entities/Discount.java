package com.bestcommerce.entities;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private int discount;

    @Column
    private LocalDate start_date;

    @Column
    private LocalDate end_date;
}

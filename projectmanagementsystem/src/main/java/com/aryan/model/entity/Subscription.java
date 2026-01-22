package com.aryan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate subscriptionStartDate;

    private LocalDate subscriptionEndDate;

    private PlanType planType;

    @OneToOne
    private User user;

    private boolean isValid;

}

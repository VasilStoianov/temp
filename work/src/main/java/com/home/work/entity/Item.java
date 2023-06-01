package com.home.work.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "item")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity{

    private String name;

    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user;
}

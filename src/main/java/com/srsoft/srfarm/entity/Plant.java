package com.srsoft.srfarm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String plantName;
    private float growTime;
    private int buyPrice;
    private int soldPrice;
    private int itemCount;

    private String plantingSprite;
    private String growingSprite;
    private String harvestSprite;
    private String inventoryIcon;
}


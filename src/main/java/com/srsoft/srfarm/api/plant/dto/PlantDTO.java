package com.srsoft.srfarm.api.plant.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlantDTO {
    private Integer id;

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

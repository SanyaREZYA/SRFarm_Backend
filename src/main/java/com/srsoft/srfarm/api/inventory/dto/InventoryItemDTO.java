package com.srsoft.srfarm.api.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryItemDTO {
    private Integer id;
    private Integer playerId;
    private Integer plantId;
    private int quantity;
}

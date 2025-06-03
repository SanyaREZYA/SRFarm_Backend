package com.srsoft.srfarm.api.player.dto;

import com.srsoft.srfarm.api.inventory.dto.InventoryItemDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerDTO {
    private Integer id;
    private String username;
    private String password;
    private int balance;
    private List<InventoryItemDTO> inventoryItems;
}

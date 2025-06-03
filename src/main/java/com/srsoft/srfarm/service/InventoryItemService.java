package com.srsoft.srfarm.service;

import com.srsoft.srfarm.api.inventory.dto.InventoryItemDTO;
import com.srsoft.srfarm.entity.InventoryItem;
import com.srsoft.srfarm.repository.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.srsoft.srfarm.utils.EntityToDTO.*;

@Service
public class InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    public List<InventoryItem> getInventoryItemsByPlayerId(Integer playerId) {
        return inventoryItemRepository.findByPlayerId(playerId);
    }

    public InventoryItem getInventoryItemById(Integer id) {
        return inventoryItemRepository.findById(id).orElse(null);
    }

    public InventoryItem addInventoryItem(InventoryItemDTO inventoryItemDTO) {
        InventoryItem inventoryItem = convertDTOToInventoryItem(inventoryItemDTO);
        return inventoryItemRepository.save(inventoryItem);
    }

    public void deleteInventoryItem(Integer id) {
        inventoryItemRepository.deleteById(id);
    }

    public void updateInventoryItem(InventoryItem inventoryItem) {
        inventoryItemRepository.save(inventoryItem);
    }
}

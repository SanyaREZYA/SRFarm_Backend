package com.srsoft.srfarm.repository;

import com.srsoft.srfarm.entity.Player;
import com.srsoft.srfarm.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
    List<InventoryItem> findByPlayerId(Integer playerId);

    void deleteByPlayerId(Integer id);
}

package com.srsoft.srfarm.repository;

import com.srsoft.srfarm.entity.Player;
import com.srsoft.srfarm.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findByUsername(String username);
}

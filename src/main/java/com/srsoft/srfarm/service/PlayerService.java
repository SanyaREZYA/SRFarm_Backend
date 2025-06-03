package com.srsoft.srfarm.service;

import com.srsoft.srfarm.api.inventory.dto.InventoryItemDTO;
import com.srsoft.srfarm.api.player.dto.PlayerDTO;
import com.srsoft.srfarm.entity.InventoryItem;
import com.srsoft.srfarm.entity.Player;
import com.srsoft.srfarm.repository.InventoryItemRepository;
import com.srsoft.srfarm.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

import static com.srsoft.srfarm.utils.EntityToDTO.*;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final InventoryItemRepository inventoryItemRepository;

    public PlayerService(PlayerRepository playerRepository, InventoryItemRepository inventoryItemRepository) {
        this.playerRepository = playerRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }


    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public Player addPlayer(PlayerDTO playerDTO) {
        return playerRepository.save(convertDTOToPlayer(playerDTO));
    }

    public Player updatePlayer(PlayerDTO playerDTO) {
        return playerRepository.save(convertDTOToPlayer(playerDTO));
    }

    @Transactional
    public Player updatePlayerWithInventory(PlayerDTO playerDTO) {
        if (playerDTO.getId() == null) {
            throw new IllegalArgumentException("PlayerDTO id must not be null");
        }

        Player existingPlayer = playerRepository.findById(playerDTO.getId())
                .orElseThrow(() -> new RuntimeException("Player not found with id " + playerDTO.getId()));

        existingPlayer.setUsername(playerDTO.getUsername());
        existingPlayer.setBalance(playerDTO.getBalance());

        Player updatedPlayer = playerRepository.save(existingPlayer);

        inventoryItemRepository.deleteByPlayerId(updatedPlayer.getId());
        if (playerDTO.getInventoryItems() != null) {
            for (InventoryItemDTO itemDTO : playerDTO.getInventoryItems()) {
                itemDTO.setPlayerId(playerDTO.getId());
                InventoryItem item = convertDTOToInventoryItem(itemDTO);
                item.setId(null);
                item.setPlayer(updatedPlayer);
                inventoryItemRepository.save(item);
            }
        }

        return updatedPlayer;
    }


    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }
}

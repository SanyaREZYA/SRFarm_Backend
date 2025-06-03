package com.srsoft.srfarm.utils;

import com.srsoft.srfarm.api.inventory.dto.InventoryItemDTO;
import com.srsoft.srfarm.api.plant.dto.PlantDTO;
import com.srsoft.srfarm.api.player.dto.PlayerDTO;
import com.srsoft.srfarm.entity.InventoryItem;
import com.srsoft.srfarm.entity.Plant;
import com.srsoft.srfarm.entity.Player;
import com.srsoft.srfarm.service.InventoryItemService;
import com.srsoft.srfarm.service.PlantService;
import com.srsoft.srfarm.service.PlayerService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityToDTO {
    private static PlantService plantService;
    private static PlayerService playerService;
    private static InventoryItemService inventoryItemService;

    public EntityToDTO(PlantService plantService, PlayerService playerService, InventoryItemService inventoryItemService) {
        this.plantService = plantService;
        this.playerService = playerService;
        this.inventoryItemService = inventoryItemService;
    }

    public static List<PlantDTO> convertPlantsToDTO(List<Plant> plants) {
        List<PlantDTO> plantDTOS = new ArrayList<>();
        if (plants == null) {
            return plantDTOS;
        }
        for (Plant plant : plants) {
            if (plant == null) {
                continue;
            }
            try {
                PlantDTO plantDTO = new PlantDTO(plant.getId(), plant.getPlantName(), plant.getGrowTime(), plant.getBuyPrice(),
                        plant.getSoldPrice(), plant.getItemCount(), plant.getPlantingSprite(), plant.getGrowingSprite(),
                        plant.getHarvestSprite(), plant.getInventoryIcon());
                plantDTOS.add(plantDTO);
            } catch (NullPointerException e) {
                System.err.println("convertPlantsToDTO: Skipping null plant");
            }
        }
        return plantDTOS;
    }

    public static PlantDTO convertPlantToDTO(Plant plant) {
        if (plant == null) {
            return new PlantDTO();
        }
        PlantDTO plantDTO;
        try {
            plantDTO = new PlantDTO(plant.getId(), plant.getPlantName(), plant.getGrowTime(), plant.getBuyPrice(),
                    plant.getSoldPrice(), plant.getItemCount(), plant.getPlantingSprite(), plant.getGrowingSprite(),
                    plant.getHarvestSprite(), plant.getInventoryIcon());
        } catch (NullPointerException e) {
            System.err.println("convertPlantToDTO: Skipping null plant");
            plantDTO = new PlantDTO();
        }
        return plantDTO;
    }

    public static Plant convertDTOToPlant(PlantDTO plantDTO) {
        if (plantDTO == null) {
            return new Plant();
        }
        Plant plant;
        try {
            plant = new Plant(plantDTO.getId(), plantDTO.getPlantName(), plantDTO.getGrowTime(), plantDTO.getBuyPrice(),
                    plantDTO.getSoldPrice(), plantDTO.getItemCount(), plantDTO.getPlantingSprite(), plantDTO.getGrowingSprite(),
                    plantDTO.getHarvestSprite(), plantDTO.getInventoryIcon());
        } catch (NullPointerException e) {
            System.err.println("convertDTOToPlant: Skipping null plant");
            plant = new Plant();
        }
        return plant;
    }

    public static List<Plant> convertDTOToPlants(List<PlantDTO> plantDTOS) {
        List<Plant> plants = new ArrayList<>();
        for (PlantDTO plantDTO : plantDTOS) {
            if (plantDTO == null) {
                continue;
            }
            try {
                Plant plant = new Plant(plantDTO.getId(), plantDTO.getPlantName(), plantDTO.getGrowTime(), plantDTO.getBuyPrice(),
                        plantDTO.getSoldPrice(), plantDTO.getItemCount(), plantDTO.getPlantingSprite(), plantDTO.getGrowingSprite(),
                        plantDTO.getHarvestSprite(), plantDTO.getInventoryIcon());
                plants.add(plant);
            } catch (NullPointerException e) {
                System.err.println("convertDTOToPlants: Skipping null plant");
            }
        }
        return plants;
    }

    public static List<InventoryItemDTO> convertInventoryItemsToDTO(List<InventoryItem> inventoryItems) {
    List<InventoryItemDTO> inventoryItemDTOS = new ArrayList<>();
    if (inventoryItems == null) {
        return inventoryItemDTOS;
    }
    for (InventoryItem inventoryItem : inventoryItems) {
        if (inventoryItem == null || inventoryItem.getPlayer() == null || inventoryItem.getPlant() == null) {
            System.err.println("convertInventoryItemsToDTO: Skipping null inventory item or related entity");
            continue;
        }
        try {
            InventoryItemDTO inventoryItemDTO = new InventoryItemDTO(inventoryItem.getId(),
                    inventoryItem.getPlayer().getId(), inventoryItem.getPlant().getId(), inventoryItem.getQuantity());
            inventoryItemDTOS.add(inventoryItemDTO);
        } catch (NullPointerException e) {
            System.err.println("convertInventoryItemsToDTO: Skipping inventory item due to missing data");
        }
    }
    return inventoryItemDTOS;
}

    public static InventoryItemDTO convertInventoryItemToDTO(InventoryItem inventoryItem) {
        if (inventoryItem == null) {
            return new InventoryItemDTO();
        }
        if (inventoryItem.getPlayer() == null || inventoryItem.getPlant() == null) {
            System.err.println("convertInventoryItemToDTO: Skipping null inventory item or related entity");
            return new InventoryItemDTO();
        }
        InventoryItemDTO inventoryItemDTO;
        try {
            inventoryItemDTO = new InventoryItemDTO(inventoryItem.getId(), inventoryItem.getPlayer().getId(),
                    inventoryItem.getPlant().getId(), inventoryItem.getQuantity());
        } catch (NullPointerException e) {
            System.err.println("convertInventoryItemToDTO: Skipping inventory item due to missing data");
            inventoryItemDTO = new InventoryItemDTO();
        }
        return inventoryItemDTO;
    }

    public static List<InventoryItem> convertDTOToInventoryItems(List<InventoryItemDTO> inventoryItemDTOs) {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        if (inventoryItemDTOs == null) {
            return inventoryItems;
        }
        for (InventoryItemDTO inventoryItemDTO : inventoryItemDTOs) {
            if (inventoryItemDTO == null) {
                System.err.println("convertDTOToInventoryItems: Skipping null inventory item");
                continue;
            }
            try {
                Player player = playerService.getPlayerById(inventoryItemDTO.getPlayerId());
                if (player == null) {
                    System.err.println("convertDTOToInventoryItems: Skipping inventory item due to missing player");
                    continue;
                }
                Plant plant = plantService.getPlantById(inventoryItemDTO.getPlantId());
                if (plant == null) {
                    System.err.println("convertDTOToInventoryItems: Skipping inventory item due to missing plant");
                    continue;
                }
                InventoryItem inventoryItem = new InventoryItem(inventoryItemDTO.getId(), player, plant, inventoryItemDTO.getQuantity());
                inventoryItems.add(inventoryItem);
            } catch (NullPointerException e) {
                System.err.println("convertDTOToInventoryItems: Skipping inventory item due to missing data");
            }
        }
        return inventoryItems;
    }

    public static InventoryItem convertDTOToInventoryItem(InventoryItemDTO inventoryItemDTO) {
        if (inventoryItemDTO == null) {
            System.err.println("convertDTOToInventoryItem: Skipping null inventory item");
            return new InventoryItem();
        }
        Player player = playerService.getPlayerById(inventoryItemDTO.getPlayerId());
        if (player == null) {
            System.err.println("convertDTOToInventoryItem: Skipping inventory item due to missing player");
            return new InventoryItem();
        }
        Plant plant = plantService.getPlantById(inventoryItemDTO.getPlantId());
        if (plant == null) {
            System.err.println("convertDTOToInventoryItem: Skipping inventory item due to missing plant");
            return new InventoryItem();
        }
        try {
            InventoryItem inventoryItem = new InventoryItem(inventoryItemDTO.getId(), player, plant, inventoryItemDTO.getQuantity());
            return inventoryItem;
        } catch (NullPointerException e) {
            System.err.println("convertDTOToInventoryItem: Skipping inventory item due to missing data");
            return new InventoryItem();
        }
    }

    public static List<PlayerDTO> convertPlayersToDTO(List<Player> players) {
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        if (players == null) {
            return playerDTOS;
        }
        for (Player player : players) {
            if (player == null) {
                System.err.println("convertPlayersToDTO: Skipping null player");
                continue;
            }
            try {
                PlayerDTO playerDTO = new PlayerDTO(player.getId(), player.getUsername(), player.getPassword(),
                        player.getBalance(), convertInventoryItemsToDTO(player.getInventoryItems()));
                playerDTOS.add(playerDTO);
            } catch (NullPointerException e) {
                System.err.println("convertPlayersToDTO: Skipping player due to missing data");
            }
        }
        return playerDTOS;
    }

    public static PlayerDTO convertPlayerToDTO(Player player) {
        if (player == null) {
            System.err.println("convertPlayerToDTO: Skipping null player");
            return new PlayerDTO();
        }
        try {
            PlayerDTO playerDTO = new PlayerDTO(player.getId(), player.getUsername(), player.getPassword(),
                    player.getBalance(), convertInventoryItemsToDTO(player.getInventoryItems()));
            return playerDTO;
        } catch (NullPointerException e) {
            System.err.println("convertPlayerToDTO: Skipping player due to missing data");
            return new PlayerDTO();
        }
    }

    public static List<Player> convertDTOToPlayers(List<PlayerDTO> playerDTOs) {
        List<Player> players = new ArrayList<>();
        if (playerDTOs == null) {
            return players;
        }
        for (PlayerDTO playerDTO : playerDTOs) {
            if (playerDTO == null) {
                System.err.println("convertDTOToPlayers: Skipping null playerDTO");
                continue;
            }
            try {
                Player player = new Player(playerDTO.getId(), playerDTO.getUsername(), playerDTO.getPassword(),
                        inventoryItemService.getInventoryItemsByPlayerId(playerDTO.getId()), playerDTO.getBalance());
                players.add(player);
            } catch (NullPointerException e) {
                System.err.println("convertDTOToPlayers: Skipping player due to missing data");
            }
        }
        return players;
    }

    public static Player convertDTOToPlayer(PlayerDTO playerDTO) {
        if (playerDTO == null) {
            System.err.println("convertDTOToPlayer: Skipping null playerDTO");
            return new Player();
        }
        List<InventoryItem> inventory = new ArrayList<>();
        if (playerDTO.getId() != null) {
            inventory = inventoryItemService.getInventoryItemsByPlayerId(playerDTO.getId());
        }
        try {
            Player player = new Player(playerDTO.getId(), playerDTO.getUsername(), playerDTO.getPassword(),
                    inventory, playerDTO.getBalance());
            return player;
        } catch (NullPointerException e) {
            System.err.println("convertDTOToPlayer: Skipping player due to missing data");
            return new Player();
        }
    }
}

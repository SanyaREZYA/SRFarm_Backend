package com.srsoft.srfarm.api.player;

import com.srsoft.srfarm.api.player.dto.PlayerDTO;
import com.srsoft.srfarm.service.PlayerService;
import com.srsoft.srfarm.utils.EntityToDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayers() {
        return EntityToDTO.convertPlayersToDTO(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Integer id) {
        return EntityToDTO.convertPlayerToDTO(playerService.getPlayerById(id));
    }

    @PostMapping("/add")
    public PlayerDTO addPlayer(@RequestBody PlayerDTO playerDTO) {
        return EntityToDTO.convertPlayerToDTO(playerService.addPlayer(playerDTO));
    }

    @PutMapping("/update")
    public PlayerDTO updatePlayer(@RequestBody PlayerDTO playerDTO) {
        return EntityToDTO.convertPlayerToDTO(playerService.updatePlayerWithInventory(playerDTO));
    }


    @DeleteMapping("/delete/{id}")
    public void deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayer(id);
    }
}

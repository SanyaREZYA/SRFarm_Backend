package com.srsoft.srfarm.api.login;

import com.srsoft.srfarm.api.login.dto.LoginRequestDTO;
import com.srsoft.srfarm.api.player.dto.PlayerDTO;
import com.srsoft.srfarm.entity.Player;
import com.srsoft.srfarm.service.PlayerService;
import com.srsoft.srfarm.utils.PasswordUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.srsoft.srfarm.utils.EntityToDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class LoginController {

    private final PlayerService playerService;

    public LoginController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        Player player = playerService.getPlayerByUsername(request.getUsername());

        if (player == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        if (!PasswordUtil.matches(request.getPassword(), player.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        PlayerDTO playerDTO = EntityToDTO.convertPlayerToDTO(player);
        return ResponseEntity.ok(playerDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequestDTO request) {
        if (request == null || request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid registration data");
        }

        if (playerService.getPlayerByUsername(request.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
        }

        try {
            String hashedPassword = PasswordUtil.hashPassword(request.getPassword());

            PlayerDTO newPlayerDTO = new PlayerDTO(null, request.getUsername(), hashedPassword, 30, null);
            Player savedPlayer = playerService.addPlayer(newPlayerDTO);
            PlayerDTO playerDTO = EntityToDTO.convertPlayerToDTO(savedPlayer);
            return ResponseEntity.ok(playerDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
}


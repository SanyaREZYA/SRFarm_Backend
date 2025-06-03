package com.srsoft.srfarm.api.plant;

import com.srsoft.srfarm.api.plant.dto.PlantDTO;
import com.srsoft.srfarm.entity.Plant;
import com.srsoft.srfarm.service.PlantService;
import com.srsoft.srfarm.utils.EntityToDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PlantController {
    private PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/plants")
    public List<PlantDTO> getAllPlants() {
        return EntityToDTO.convertPlantsToDTO(plantService.getAllPlants());
    }

    @PostMapping("/add-plant")
    public Plant addPlant(@RequestBody PlantDTO plantDTO) {
        return plantService.addPlant(plantDTO);
    }
}

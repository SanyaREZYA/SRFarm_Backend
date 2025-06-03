package com.srsoft.srfarm.service;

import com.srsoft.srfarm.api.plant.dto.PlantDTO;
import com.srsoft.srfarm.entity.Plant;
import com.srsoft.srfarm.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.srsoft.srfarm.utils.EntityToDTO.*;

@Service
public class PlantService {
    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Plant getPlantById(int id) {
        return plantRepository.findById(id).orElse(null);
    }

    public Plant getPlantByPlantName(String plantName) {
        return plantRepository.findByPlantName(plantName);
    }

    public Plant addPlant(PlantDTO plantDTO) {
        return plantRepository.save(convertDTOToPlant(plantDTO));
    }

    public void deletePlant(int id) {
        plantRepository.deleteById(id);
    }

    public Plant updatePlant(Plant plant) {
        return plantRepository.save(plant);
    }
}

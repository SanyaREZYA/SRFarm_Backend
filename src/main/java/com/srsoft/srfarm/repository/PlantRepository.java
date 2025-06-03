package com.srsoft.srfarm.repository;

import com.srsoft.srfarm.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
    boolean existsByPlantName(String plantName);

    Plant findByPlantName(String plantName);
}

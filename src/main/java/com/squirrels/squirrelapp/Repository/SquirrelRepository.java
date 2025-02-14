package com.squirrels.squirrelapp.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.squirrels.squirrelapp.Model.Squirrel;

@Repository
public interface SquirrelRepository extends JpaRepository<Squirrel, Long> {

    @Query("SELECT DISTINCT s.areaName FROM Squirrel s")
    List<String> findDistinctAreas();

    @Query("SELECT DISTINCT s.parkName FROM Squirrel s")
    List<String> findDistinctParks();

    @Query("SELECT DISTINCT s.furColor FROM Squirrel s")
    List<String> findDistinctFurColors();

    @Query("SELECT s FROM Squirrel s WHERE " +
       "(:area IS NULL OR LOWER(s.areaName) LIKE LOWER(CONCAT('%', :area, '%'))) AND " +
       "(:park IS NULL OR LOWER(s.parkName) LIKE LOWER(CONCAT('%', :park, '%'))) AND " +
       "(:color IS NULL OR LOWER(s.furColor) LIKE LOWER(CONCAT('%', :color, '%')))")
    List<Squirrel> searchByFilters(
            @Param("area") String area, 
            @Param("park") String park, 
            @Param("color") String color
            );
}

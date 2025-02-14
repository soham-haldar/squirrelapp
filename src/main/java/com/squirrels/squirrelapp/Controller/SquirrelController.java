package com.squirrels.squirrelapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.squirrels.squirrelapp.Model.Squirrel;
import com.squirrels.squirrelapp.Repository.SquirrelRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/squirrels")
@CrossOrigin(origins = "*")
public class SquirrelController {

    @Autowired
    private SquirrelRepository repository;

    @GetMapping("")
    public List<Squirrel> getAllSquirrels() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Squirrel> getSquirrelById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Squirrel addSquirrel(@RequestBody Squirrel squirrel) {
        return repository.save(squirrel);
    }

    @PutMapping("/{id}")
    public Squirrel updateSquirrel(@PathVariable Long id, @RequestBody Squirrel updatedSquirrel) {
        return repository.findById(id).map(squirrel -> {
            squirrel.setAreaName(updatedSquirrel.getAreaName());
            squirrel.setAreaId(updatedSquirrel.getAreaId());
            squirrel.setParkName(updatedSquirrel.getParkName());
            squirrel.setParkId(updatedSquirrel.getParkId());
            squirrel.setSquirrelId(updatedSquirrel.getSquirrelId());
            squirrel.setFurColor(updatedSquirrel.getFurColor());
            squirrel.setLocation(updatedSquirrel.getLocation());
            squirrel.setActivities(updatedSquirrel.getActivities());
            squirrel.setInteractions(updatedSquirrel.getInteractions());
            squirrel.setNotes(updatedSquirrel.getNotes());
            return repository.save(squirrel);
        }).orElseThrow(() -> new RuntimeException("Squirrel not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteSquirrel(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/areas")
    public List<String> getDistinctAreas() {
        return repository.findDistinctAreas();
    }

    @GetMapping("/parks")
    public List<String> getDistinctParks() {
        return repository.findDistinctParks();
    }

    @GetMapping("/colors")
    public List<String> getDistinctFurColors() {
        return repository.findDistinctFurColors();
    }

    @GetMapping("/filter")
    public List<Squirrel> filterSquirrels(
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String park,
            @RequestParam(required = false) String color){
        return repository.searchByFilters(area, park, color);
    }

}

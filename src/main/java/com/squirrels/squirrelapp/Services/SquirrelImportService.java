package com.squirrels.squirrelapp.Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squirrels.squirrelapp.Model.Squirrel;
import com.squirrels.squirrelapp.Repository.SquirrelRepository;

@Service
public class SquirrelImportService {

    @Autowired
    private SquirrelRepository repository;

    public void importCSV(File file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))){
            String line;
            boolean firstline = true;
            List<Squirrel> squirrels = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (firstline) {
                    firstline = false;
                    // Skipping Header
                    continue;
                }
                String[] data = line.split(",");
                Squirrel squirrel = new Squirrel();
                squirrel.setAreaName(data[0].trim());
                squirrel.setAreaId(data[1].trim());
                squirrel.setParkName(data[2].trim());
                squirrel.setParkId(data[3].trim());
                squirrel.setSquirrelId(data[4].trim());
                squirrel.setFurColor(data[5].trim());
                squirrel.setLocation(data[6].trim());   
                squirrel.setActivities(data[7].trim());
                squirrel.setInteractions(data[8].trim());
                squirrel.setNotes(data[9].trim());


                // Adding Parsed Squirrel to Database
                squirrels.add(squirrel);
            }
            repository.saveAll(squirrels);

        } catch (Exception e) {
            throw new RuntimeException("Error Reading From CSV" + e.getMessage());
        }
    }
}

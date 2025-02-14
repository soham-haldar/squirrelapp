package com.squirrels.squirrelapp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="squirrels")
@NoArgsConstructor
@AllArgsConstructor
public class Squirrel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaName;
    private String areaId;
    private String parkName;
    private String parkId;
    private String squirrelId;
    private String furColor;
    private String location;
    private String activities;
    private String interactions;
    private String notes;
}

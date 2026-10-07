package com.example.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity 
@Data 
public class Company{

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String website;

    @ManyToOne 
    @JoinColumn(name="owner_id")
    private User owner;
}
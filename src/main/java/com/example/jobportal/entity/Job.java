package com.example.jobportal.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data//lombok this autogenerates the code for getters and setters and reduce the code upton40 lines for these 7 fields
public class Job {
    @Id //primary key
    @GeneratedValue(strategy=GenerationType.IDENTITY)//database to autoincrement the id
    private Long id;
    private String title;
    private String description;
    private String location;
    private String salaryRange;
    private String jobType;
    private String status;
}
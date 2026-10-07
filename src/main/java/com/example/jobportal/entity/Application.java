package com.example.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity 
@Data public class Application{

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "application_id")
    private User applicant;
    private String status;
    private String resumeLink;

}
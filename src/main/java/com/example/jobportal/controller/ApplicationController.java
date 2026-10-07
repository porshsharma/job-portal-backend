package com.example.jobportal.controller;

import com.example.jobportal.entity.Application;
import com.example.jobportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/applications")
public class ApplicationController{

    @Autowired  
    private ApplicationRepository applicationRepository;
    @PostMapping 
    public Application applyToJob(@RequestBody Application application){
        application.setStatus("APPLIED");
        return applicationRepository.save(application);
    }
    @GetMapping 
    public List<Application> geAllApplications(){
        return applicationRepository.findAll();
    }
    @PutMapping ("/{id}/status")
    public  Application updateStatus(@PathVariable Long id,@RequestBody String status){
        Application application=applicationRepository.findById(id).orElseThrow(() ->new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);


    }
}
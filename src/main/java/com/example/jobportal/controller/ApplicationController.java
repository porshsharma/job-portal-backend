package com.example.jobportal.controller;

import com.example.jobportal.entity.Application;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.ApplicationRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Application applyToJob(@RequestBody Application application, Authentication authentication) {
        User applicant = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        application.setApplicant(applicant);
        application.setStatus("APPLIED");
        return applicationRepository.save(application);
    }

    @GetMapping
    public List<Application> getApplications(Authentication authentication) {
        boolean isApplicant = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_APPLICANT"));
        if (isApplicant) {
            return applicationRepository.findByApplicantEmail(authentication.getName());
        }
        return applicationRepository.findAll();
    }

    @PutMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @RequestBody String status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
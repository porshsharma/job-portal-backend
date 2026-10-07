package com.example.jobportal.controller;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Company createCompany(@RequestBody Company company, Authentication authentication) {
        String email = authentication.getName();
        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Logged-in user not found"));
        company.setOwner(owner);
        return companyRepository.save(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
    }
}
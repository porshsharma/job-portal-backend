package com.example.jobportal.controller;

import com.example.jobportal.entity.Job;
import com.example.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
        
    }
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setLocation(updatedJob.getLocation());
        job.setSalaryRange(updatedJob.getSalaryRange());
        job.setJobType(updatedJob.getJobType());
        job.setStatus(updatedJob.getStatus());
        return jobRepository.save(job);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return "Job deleted with id: " + id;
    }
}


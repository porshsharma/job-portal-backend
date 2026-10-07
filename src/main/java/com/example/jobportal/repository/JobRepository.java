package com.example.jobportal.repository;//which package this files belongs to
import com.example.jobportal.entity.Job;//imported our own class here
import org.springframework.data.jpa.repository.JpaRepository;
public interface JobRepository extends JpaRepository<Job, Long> {//interface is like a contact ;instead of writing an actual logic we just declaring what we neeed 

    
}


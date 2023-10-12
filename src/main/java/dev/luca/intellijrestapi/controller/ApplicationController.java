package dev.luca.intellijrestapi.controller;

import dev.luca.intellijrestapi.model.SocialHousingApplication;
import dev.luca.intellijrestapi.repository.SocialHousingApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final SocialHousingApplicationRepository repository;

    public ApplicationController(SocialHousingApplicationRepository repository) {
        this.repository = repository;
    }

    // GET http://localhost:3000/api/v1/applications
    @GetMapping
    public List<SocialHousingApplication> getApplications() {
        return repository.getApplications();
    }

    // GET http://localhost:3000/api/v1/applications/186763b6-30a6-4fd0-bec5-e42f9efd3f86
    @GetMapping("/{id}")
    public SocialHousingApplication getApplicationById(@PathVariable String id) {
        return repository.getApplicationById(id);
    }

    // POST http://localhost:3000/api/v1/applications
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public SocialHousingApplication createApplication(@Valid @RequestBody SocialHousingApplication application) {
        repository.createApplication(application);
        return application;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateApplication
            (@RequestBody SocialHousingApplication application, @PathVariable String id) {
        repository.updateApplicationById(id, application);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable String id) {
        repository.deleteApplicationById(id);
    }
}

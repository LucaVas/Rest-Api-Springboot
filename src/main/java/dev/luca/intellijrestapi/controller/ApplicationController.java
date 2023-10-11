package dev.luca.intellijrestapi.controller;

import dev.luca.intellijrestapi.model.SocialHousingApplication;
import dev.luca.intellijrestapi.repository.SocialHousingApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    // POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public SocialHousingApplication createApplication(@RequestBody SocialHousingApplication application) {
        repository.createApplication(application);
        return application;
    }

}

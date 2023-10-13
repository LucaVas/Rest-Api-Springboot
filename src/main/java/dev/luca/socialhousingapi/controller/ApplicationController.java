package dev.luca.socialhousingapi.controller;

import dev.luca.socialhousingapi.model.SocialHousingApplication;
import dev.luca.socialhousingapi.service.SocialHousingApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final SocialHousingApplicationService service;

    public ApplicationController(SocialHousingApplicationService service) {
        this.service = service;
    }

    // GET http://localhost:3000/api/v1/applications
    @GetMapping
    public ResponseEntity<List<SocialHousingApplication>> getApplications() {
        List<SocialHousingApplication> applications = service.getApplications();
        if (applications == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(applications);
        }
    }

    // GET http://localhost:3000/api/v1/applications/186763b6-30a6-4fd0-bec5-e42f9efd3f86
    @GetMapping("/{id}")
    public SocialHousingApplication getApplicationById(@PathVariable String id) {
        return service.getApplicationById(id);
    }

    // POST http://localhost:3000/api/v1/applications
    @PostMapping("")
    public ResponseEntity<SocialHousingApplication> createApplication(@Valid @RequestBody SocialHousingApplication application) {
        SocialHousingApplication createdApplication = service.createApplication(application);
        if (createdApplication == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdApplication.id())
                    .toUri();

            return ResponseEntity.created(uri).body(createdApplication);
        }
    }

    // PUT http://localhost:3000/api/v1/applications/186763b6-30a6-4fd0-bec5-e42f9efd3f86
    @PutMapping("/{id}")
    public ResponseEntity<SocialHousingApplication> updateApplication
            (@RequestBody SocialHousingApplication application, @PathVariable String id) {
        SocialHousingApplication updatedApplication = service.updateApplicationById(id, application);
        if (updatedApplication == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedApplication);
        }
    }

    // DELETE http://localhost:3000/api/v1/applications/186763b6-30a6-4fd0-bec5-e42f9efd3f86
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplication(@PathVariable String id) {
        service.deleteApplicationById(id);
        return ResponseEntity.noContent().build();
    }
}

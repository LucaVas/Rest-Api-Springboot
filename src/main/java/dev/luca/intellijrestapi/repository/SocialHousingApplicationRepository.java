package dev.luca.intellijrestapi.repository;

import dev.luca.intellijrestapi.model.SocialHousingApplication;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Encapsulate data access (similar to DTO pattern)
 */

@Repository
public class SocialHousingApplicationRepository {
    private List<SocialHousingApplication> applications = new ArrayList<>();
    public SocialHousingApplicationRepository() {
        Timestamp timestamp = Timestamp.from(Instant.now());
        this.applications.add(
                new SocialHousingApplication(UUID.randomUUID().toString(), "New", timestamp, timestamp)
        );
    }

    public List<SocialHousingApplication> getApplications() {
        return applications;
    }

    public SocialHousingApplication getApplicationById(String id) {
        return applications.stream()
                .filter(app -> Objects.equals(id, app.getId()))
                .findFirst()
                .orElse(null);
    }

    public SocialHousingApplication createApplication(SocialHousingApplication application) {
        this.applications.add(application);
        return application;
    }
}
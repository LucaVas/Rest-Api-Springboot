package dev.luca.socialhousingapi.repository;

import dev.luca.socialhousingapi.model.SocialHousingApplication;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Encapsulate data access (similar to DTO pattern)
 */

@Repository
public class SocialHousingApplicationRepository {
    private List<SocialHousingApplication> applications = new ArrayList<>();
    public SocialHousingApplicationRepository() {
    }

    public List<SocialHousingApplication> getApplications() {
        return applications;
    }

    public SocialHousingApplication getApplicationById(String id) {
        return applications.stream()
                .filter(app -> Objects.equals(id, app.id()))
                .findFirst()
                .orElse(null);
    }

    public SocialHousingApplication createApplication(SocialHousingApplication application) {
        this.applications.add(application);
        return application;
    }

    public SocialHousingApplication updateApplicationById(String id, SocialHousingApplication application) {
        this.applications  = applications.stream()
                .map(app -> {
                    if (Objects.equals(app.id(), id)) {
                        return application;
                    }
                    return app;
                })
                .collect(Collectors.toCollection(ArrayList::new));
        return applications.stream()
                .filter(app -> Objects.equals(app.id(), id))
                .findFirst()
                .orElse(null);
    }

    public void deleteApplicationById(String id) {
        this.applications =
                applications.stream()
                        .filter(app -> !Objects.equals(app.id(), id))
                        .collect(Collectors.toCollection(ArrayList::new));
    }
}

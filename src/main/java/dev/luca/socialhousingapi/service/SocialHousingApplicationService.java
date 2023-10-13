package dev.luca.socialhousingapi.service;

import dev.luca.socialhousingapi.model.SocialHousingApplication;
import dev.luca.socialhousingapi.repository.SocialHousingApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialHousingApplicationService {

    private final SocialHousingApplicationRepository repository;

    public SocialHousingApplicationService(SocialHousingApplicationRepository repository) {
        this.repository = repository;
    }

    public List<SocialHousingApplication> getApplications() {
        return repository.getApplications();
    }

    public SocialHousingApplication createApplication(SocialHousingApplication application) {
        return repository.createApplication(application);
    }

    public SocialHousingApplication getApplicationById(String id) {
        return repository.getApplicationById(id);
    }

    public SocialHousingApplication updateApplicationById(String id, SocialHousingApplication updatedApplication) {
        SocialHousingApplication newApplication = repository.updateApplicationById(id, updatedApplication);
        return newApplication;
    }

    public void deleteApplicationById(String id) {
        repository.deleteApplicationById(id);
    }
}

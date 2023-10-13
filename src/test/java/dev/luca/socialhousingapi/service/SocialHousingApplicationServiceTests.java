package dev.luca.socialhousingapi.service;

import dev.luca.socialhousingapi.model.SocialHousingApplication;
import dev.luca.socialhousingapi.repository.SocialHousingApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SocialHousingApplicationServiceTests {

    @Test
    public void get_applications_returns_no_applications_if_list_is_empty() {
        // Given I have a new service
        SocialHousingApplicationService service = new SocialHousingApplicationService(new SocialHousingApplicationRepository());

        // When I ask for applications
        List<SocialHousingApplication> applications = service.getApplications();

        // Then I have no applications
        assertNotNull(service);
        assertEquals(0, applications.size());
    }

    @Test
    public void get_applications_returns_applications_if_any_is_present() {
        // Given a new service and a set of applications
        Timestamp timestamp = Timestamp.from(Instant.now());
        SocialHousingApplication application = new SocialHousingApplication("100001", "New", timestamp, timestamp);
        SocialHousingApplicationRepository repository = new SocialHousingApplicationRepository();
        repository.createApplication(application);
        SocialHousingApplicationService service = new SocialHousingApplicationService(repository);

        // When I get the application list
        List<SocialHousingApplication> applications = service.getApplications();

        // Then I have the applications I added
        assertNotNull(applications);
        assertEquals(1, applications.size());
        assertEquals("100001", applications.get(0).id());
        assertEquals("New", applications.get(0).status());
        assertEquals(timestamp, applications.get(0).createdAt());
        assertEquals(timestamp, applications.get(0).updatedAt());
    }

    @Test
    public void create_applications_adds_applications_to_repository() {
        // Given a service
        SocialHousingApplicationService service = new SocialHousingApplicationService(new SocialHousingApplicationRepository());

        // When I try to create new applications
        Timestamp timestamp = Timestamp.from(Instant.now());
        SocialHousingApplication application = new SocialHousingApplication("100002", "Blocked", timestamp, timestamp);
        SocialHousingApplication applicationCreated = service.createApplication(application);

        List<SocialHousingApplication> applications = service.getApplications();

        // Then I can get the application
        assertNotNull(applications);
        assertEquals(1, applications.size());
        assertEquals("100002", applicationCreated.id());
        assertEquals("Blocked", applicationCreated.status());
        assertEquals(timestamp, applicationCreated.createdAt());
        assertEquals(timestamp, applicationCreated.updatedAt());
    }

    @Test
    public void get_application_by_id_returns_application() {
        // Given a new service and a set of applications
        Timestamp timestamp = Timestamp.from(Instant.now());
        SocialHousingApplication newApplication = new SocialHousingApplication("100003", "Closed", timestamp, timestamp);
        SocialHousingApplicationRepository repository = new SocialHousingApplicationRepository();
        repository.createApplication(newApplication);
        SocialHousingApplicationService service = new SocialHousingApplicationService(repository);

        // When I get the application list
        SocialHousingApplication application = service.getApplicationById("100003");

        // Then I have the applications I added
        assertNotNull(application);
        assertEquals("100003", application.id());
        assertEquals("Closed", application.status());
        assertEquals(timestamp, application.createdAt());
        assertEquals(timestamp, application.updatedAt());
    }

    @Test
    public void update_by_id_changes_application_with_id() {
        // Given a new service and an application
        Timestamp timestamp = Timestamp.from(Instant.now());
        SocialHousingApplication newApplication = new SocialHousingApplication("100004", "New", timestamp, timestamp);
        SocialHousingApplicationRepository repository = new SocialHousingApplicationRepository();
        repository.createApplication(newApplication);
        SocialHousingApplicationService service = new SocialHousingApplicationService(repository);

        // When I create a new application, and update the existing one
        Timestamp updatedTimestamp = Timestamp.from(Instant.now());
        SocialHousingApplication updatedApplication = new SocialHousingApplication("100005", "Closed", updatedTimestamp, updatedTimestamp);
        service.updateApplicationById("100004", updatedApplication);
        SocialHousingApplication app1 = service.getApplicationById("100004");
        SocialHousingApplication app2 = service.getApplicationById("100005");

        // Then I retrieve the updated application, and not the old one
        assertNull(app1);
        assertNotNull(app2);
        assertEquals("100005", app2.id());
        assertEquals("Closed", app2.status());
        assertEquals(updatedTimestamp, app2.createdAt());
        assertEquals(updatedTimestamp, app2.updatedAt());
    }

    @Test
    public void delete_by_id_deletes_application_with_id() {
        // Given a new service and an application
        Timestamp timestamp = Timestamp.from(Instant.now());
        SocialHousingApplication newApplication = new SocialHousingApplication("100006", "Blocked", timestamp, timestamp);
        SocialHousingApplicationRepository repository = new SocialHousingApplicationRepository();
        repository.createApplication(newApplication);
        SocialHousingApplicationService service = new SocialHousingApplicationService(repository);

        // When I delete the application and return the applications
        service.deleteApplicationById("100006");
        List<SocialHousingApplication> applications = service.getApplications();
        SocialHousingApplication deletedApplication = service.getApplicationById("100006");

        // Then I retrieve the updated application, and not the old one
        assertEquals(0, applications.size());
        assertNull(deletedApplication);
    }


}

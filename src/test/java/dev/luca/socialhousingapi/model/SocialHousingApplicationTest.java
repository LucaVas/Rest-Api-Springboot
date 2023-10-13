package dev.luca.socialhousingapi.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Representation of an Application
 */

@SpringBootTest
public class SocialHousingApplicationTest {

    @Test
    void should_create_new_application() {
        // Given initial parameters (or an application instance)
        Timestamp timestamp = Timestamp.from(Instant.now());

        // When I add the properties
        SocialHousingApplication application = new SocialHousingApplication("100009", "New", timestamp, timestamp);

        // Then I check if instance is not null, and properties are valid
        assertNotNull(application);
        assertEquals("New", application.status(), "Statuses do not match");
        assertEquals("100009", application.id(), "Ids do not match");

    }
}

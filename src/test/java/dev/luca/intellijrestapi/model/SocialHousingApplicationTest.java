package dev.luca.intellijrestapi.model;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Representation of an Application
 */

public class SocialHousingApplicationTest {

    @Test
    void create_new_application() {
        // Given initial parameters (or an application instance)
        Timestamp timestamp = Timestamp.from(Instant.now());

        // When I add the properties
        SocialHousingApplication application = new SocialHousingApplication(UUID.randomUUID().toString(), "New", timestamp, timestamp);

        // Then I check if instance is not null, and properties are valid
        assertNotNull(application);
        assertEquals("New", application.getStatus());
    }
}

package dev.luca.socialhousingapi.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicantTest {

    @Test
    void should_create_new_applicant() {
        // Given
        Timestamp timestamp = Timestamp.from(Instant.now());

        // When
        Applicant applicant = new Applicant(UUID.randomUUID().toString(), "John", "Doe", "App123", timestamp, timestamp);

        // Then
        assertNotNull(applicant);
        assertTrue(applicant.getClass().isRecord());
        assertEquals("John", applicant.firstName());
        assertEquals("Doe", applicant.lastName());
        assertEquals("App123", applicant.applicationId());
        assertEquals(timestamp, applicant.createdAt());
        assertEquals(timestamp, applicant.updatedAt());
    }

    @Test
    void null_name_and_surname_throw_illegal_argument_exception() {
        // Given
        Timestamp timestamp = Timestamp.from(Instant.now());

        // When I create a new applicant
        Throwable exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Applicant(UUID.randomUUID().toString(), null, null, "App123", timestamp, timestamp));

        // Then exception is thrown
        assertEquals("First and last name cannot be null", exception.getMessage());
    }

}

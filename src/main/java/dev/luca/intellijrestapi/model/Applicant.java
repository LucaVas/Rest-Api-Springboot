package dev.luca.intellijrestapi.model;

import java.sql.Timestamp;

/**
 * Representation of an Applicant
 */

public record Applicant
        (String id, String firstName, String lastName, String applicationId, Timestamp createdAt, Timestamp updatedAt) {

    public Applicant {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First and last name cannot be null");
        }
    }
}

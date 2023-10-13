package dev.luca.socialhousingapi.model;

import java.sql.Timestamp;

public record SocialHousingApplication(String id, String status, Timestamp createdAt, Timestamp updatedAt) {

}

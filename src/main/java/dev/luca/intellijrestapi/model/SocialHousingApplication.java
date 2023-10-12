package dev.luca.intellijrestapi.model;

import jakarta.validation.constraints.NotEmpty;

import java.sql.Timestamp;
import java.util.Objects;

public class SocialHousingApplication {

    private final String id;
    @NotEmpty
    private final String status;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public SocialHousingApplication(String id, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SocialHousingApplication{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialHousingApplication that = (SocialHousingApplication) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdAt, updatedAt);
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

}

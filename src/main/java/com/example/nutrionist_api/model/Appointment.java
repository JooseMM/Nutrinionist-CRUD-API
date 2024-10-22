package com.example.nutrionist_api.model;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.UUID;
import org.springframework.boot.context.properties.bind.Name;
import com.example.nutrionist_api.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Future;


@Entity
public class Appointment {
    @Id
    @Column(columnDefinition = "CHAR(36)")
    private String id;
    @Column(name = "client_name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String clientName;
    @Column(name = "client_diagnosis", columnDefinition = "VARCHAR(255)", nullable = false)
    private String clientDiagnosis;
    @Column(name = "client_goal", columnDefinition = "VARCHAR(255)", nullable = false)
    private String clientGoal;
    @Column(name = "client_age", nullable = false)
    private int clientAge;
    @Enumerated(EnumType.STRING)
    @Column(name = "client_sex", columnDefinition = "ENUM('FEMALE','MALE')", nullable = false)
    private Gender clientSex;
    @Column(name = "client_email", columnDefinition = "VARCHAR(255)", nullable = false)
    private String clientEmail;
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;
    @Column(name = "updated_at", updatable = true)
    private ZonedDateTime updatedAt;
    @Future
    @Column(name = "date_time", nullable = false)
    private ZonedDateTime dateTime;
    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private boolean completed;
    
    public Appointment() {}

    public Appointment(String name, int age, Gender sex, String email, String diagnosis, String goal, ZonedDateTime dateTime) {
	this.clientName = name;
	this.clientAge = age;
    this.clientSex = sex;
	this.clientDiagnosis = diagnosis;
	this.clientEmail = email;
	this.clientGoal = goal;
	this.dateTime = dateTime;
    }

    @PrePersist
    public void onCreate() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = ZonedDateTime.now(ZoneId.of("America/Santigo"));
        updatedAt = createdAt; 
        this.completed = false;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = ZonedDateTime.now(ZoneId.of("America/Santigo"));
    }
     /* -- Getters -- */
    public String getId() {
	return this.id;
    }
    public String getClientName() {
	return this.clientName;
    }
    public int getClientAge() {
	return this.clientAge;
    }
    public Gender getClientSex() {
	return this.clientSex;
    }
    public String getClientEmail() {
	return this.clientEmail;
    }
    public String getClientDiagnosis() {
	return this.clientDiagnosis;
    }
    public String getClientGoal() {
	return this.clientGoal;
    }
    public ZonedDateTime getCreationDate() {
	return this.createdAt;
    }
    public ZonedDateTime getUpdateDate() {
	return this.updatedAt;
    }
    public boolean getCompleted() {
	return this.completed;
    }
    public ZonedDateTime getDateTime() {
	return this.dateTime;
    }
     /* -- Setters -- */
    public void setClientName(String newName) {
	this.clientName = newName;
    }
    public void setClientSex(Gender newSex) {
	this.clientSex = newSex;
    }
    public void setClientEmail(String newEmail) {
	this.clientEmail = newEmail;
    }
    public void setClientDiagnosis(String newDiagnosis) {
	this.clientDiagnosis = newDiagnosis;
    }
    public void setClientAge(int newAge) {
	this.clientAge = newAge;
    }
    public void setClientGoal(String newGoal) {
	this.clientGoal = newGoal;
    }
    public void setCompleted(Boolean completed) {
	this.completed = completed;
    }
    public void setDateTime(ZonedDateTime newDate) {
	this.dateTime = newDate;
    }

}
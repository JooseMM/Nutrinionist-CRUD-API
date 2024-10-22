package com.example.nutrionist_api.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import com.example.nutrionist_api.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


@Entity
public class Appointment {
    @Id
    @Column(columnDefinition = "CHAR(36)")
    private String id;
    @Column(name = "client_name", columnDefinition = "VARCHAR(100)")
    private String clientName;
    @Column(name = "client_diagnosis", columnDefinition = "VARCHAR(255)")
    private String clientDiagnosis;
    @Column(name = "client_goal", columnDefinition = "VARCHAR(255)")
    private String clientGoal;
    @Column(name = "client_age")
    private int clientAge;
    @Enumerated(EnumType.STRING)
    @Column(name = "client_sex", columnDefinition = "ENUM('FEMALE','MALE')")
    private Gender clientSex;
    @Column(name = "client_email", columnDefinition = "VARCHAR(255)")
    private String clientEmail;
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
    @Column(name = "updated_at", updatable = true)
    private OffsetDateTime updatedAt;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean completed;
    
    public Appointment() {}

    public Appointment(String name, int age, Gender sex, String email, String diagnosis, String goal) {
	this.clientName = name;
	this.clientAge = age;
    this.clientSex = sex;
	this.clientDiagnosis = diagnosis;
	this.clientEmail = email;
	this.clientGoal = goal;
    }

    @PrePersist
    public void onCreate() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = OffsetDateTime.now();
        updatedAt = createdAt; 
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = OffsetDateTime.now();
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
    public OffsetDateTime getCreationDate() {
	return this.createdAt;
    }
    public OffsetDateTime getUpdateDate() {
	return this.updatedAt;
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

}
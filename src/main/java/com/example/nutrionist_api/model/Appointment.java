package com.example.nutrionist_api.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private String clientName, clientDiagnosis, clientGoal;
    private int clientAge;
    private String clientSex;

    Appointment() {}

    public Appointment(String name, int age, String sex, String diagnosis, String goal) {
	this.creationDate = LocalDate.now();
	this.updateDate = this.creationDate;
	this.clientName = name;
	this.clientAge = age;
	this.clientDiagnosis = diagnosis;
	this.clientGoal = goal;
    }

     /* -- Getters -- */
    public Long getId() {
	return this.id;
    }
    public String getName() {
	return this.clientName;
    }
    public int getAge() {
	return this.clientAge;
    }
    public String getSex() {
	return this.clientSex;
    }
    public String getDiagnosis() {
	return this.clientDiagnosis;
    }
    public LocalDate getCreationDate() {
	return this.creationDate;
    }
    public LocalDate getUpdateDate() {
	return this.updateDate;
    }
    public String getClientGoal() {
	return this.clientGoal;
    }
     /* -- Setters -- */
    public void setClientName(String newName) {
	this.clientName = newName;
    }
    public void setClientSex(String newSex) {
	this.clientSex = newSex;
    }
    public void setDiagnosis(String newDiagnosis) {
	this.clientDiagnosis = newDiagnosis;
    }
    public void setUpdateDate(LocalDate newDate) {
	this.updateDate = newDate;
    }
    public void setClientAge(int newAge) {
	this.clientAge = newAge;
    }
    public void setClientGoal(String newGoal) {
	this.clientGoal = newGoal;
    }

}

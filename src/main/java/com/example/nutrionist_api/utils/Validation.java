package com.example.nutrionist_api.utils;

import java.util.regex.Pattern;

import com.example.nutrionist_api.model.Appointment;

public final class Validation {

    // Private constructor to prevent instantiation
    private Validation() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static void isNewAppointmentValid(Appointment newAppointment) {
            if(isClientNameInvalid(newAppointment.getClientName())) {
                throw new IllegalArgumentException("Client name cannot be null, empty or contain any other characters than letters");
            }
            if(isClientAgeValid(newAppointment.getClientAge())) {
                throw new IllegalArgumentException("Client age cannot be 0, lower than 0 or greater than 150");
            }
            if(isInvalidEmail(newAppointment.getClientEmail())) {
                throw new IllegalArgumentException("Client email is not a valid email");
            }
            if(containsInvalidCharacters(newAppointment.getClientDiagnosis()) && containsInvalidCharacters(newAppointment.getClientGoal())) {
                throw new IllegalArgumentException("Diagnosis and goal can only contain letters, numbers, spaces, commas, parentheses, and full stops.");
            }
    }

    // utility validation
    private static boolean isInvalidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email == null || email.isEmpty() || !pattern.matcher(email).matches();
    }
    private static boolean isClientNameInvalid(String name) {
        String nameRegex ="^[A-Za-z]+( [A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(nameRegex);
        return name == null || name.isEmpty() || !pattern.matcher(name).matches();
    }
    private static boolean isClientAgeValid(int age) {
        return age <= 0 || age >= 150;
    }
    private static boolean containsInvalidCharacters(String text) {
        String paragraphPatter ="^[A-Za-z0-9 ,().]+$";
        Pattern pattern = Pattern.compile(paragraphPatter);
        return text == null || text.isEmpty() || !pattern.matcher(text).matches();
    }
}
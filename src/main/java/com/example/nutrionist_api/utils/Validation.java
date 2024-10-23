package com.example.nutrionist_api.utils;

import java.util.UUID;
import java.util.regex.Pattern;
import com.example.nutrionist_api.model.Appointment;

public final class Validation {
    // Private constructor to prevent instantiation
    private Validation() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    // validates an vaid appointment object or throw an exception
    public static void isNewAppointmentValid(Appointment newAppointment) {
        if (isUUIDInvalid(newAppointment.getId())) {
            throw new IllegalArgumentException(
                    "The provided ID is not a valid UUID. Please ensure it follows the format: 123e4567-e89b-12d3-a456-426614174000.");
        }
        if (isClientNameInvalid(newAppointment.getClientName())) {
            throw new IllegalArgumentException(
                    "The client name must be provided. It cannot be null, empty, or contain any characters other than letters (A-Z, a-z).");
        }
        if (isClientAgeValid(newAppointment.getClientAge())) {
            throw new IllegalArgumentException("The client age must be between 1 and 150. It cannot be 0 or negative.");
        }
        if (isInvalidEmail(newAppointment.getClientEmail())) {
            throw new IllegalArgumentException("The client email address is not valid. Please provide a correctly formatted email address.");
        }
        if (containsInvalidCharacters(newAppointment.getClientDiagnosis())
                && containsInvalidCharacters(newAppointment.getClientGoal())) {
            throw new IllegalArgumentException(
                    "Diagnosis and goal can only contain letters, numbers, spaces, commas, parentheses, and periods. Please check your input.");
        }
    }
    // validates an UUID String or throw an exception
    public static void isUUID(String id) {
        try {
            UUID.fromString(id);
        } catch(Exception e) {
            throw new IllegalArgumentException(
                    "The provided ID is not a valid UUID. Please ensure it follows the format: 123e4567-e89b-12d3-a456-426614174000.");
        }
    }
    // utility validation
    private static boolean isInvalidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email == null || email.isEmpty() || !pattern.matcher(email).matches();
    }
    private static boolean isUUIDInvalid (String id) {
        try {
            UUID.fromString(id);
            return false;
        } catch(Exception e) {
            return true;
        }
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
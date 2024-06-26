package com.beste.veterinary.core;

public class Message {
    public static final String CREATED = "Data successfully saved";
    public static final String UPDATED = "Data successfully updated";
    public static final String DELETED = "Data successfully deleted";
    public static final String NOT_FOUND = "Data not found";
    public static final String ALREADY_EXIST = "Data already exists";
    public static final String OK = "Process successfully executed";
    public static final String DATE_ERROR = "The protection finish date can't be before the protection start date";
    public static final String VACCINE_ERROR = "The protective date of this vaccine has not expired";
    public static final String DOCTOR_ERROR = "This doctor is unavailable at this date";
    public static final String APP0_TIME_ERROR = "Appointments can be set at the beginning of each hour";
    public static final String APP0_EXIST_ERROR = "Appointment for this animal exists at this hour";
    public static final String APP0_NOT_AVA_ERROR = "No appointment available at this hour";
    public static final String ANIMAL_SELECT_ID_NULL = "An animal should be selected";
    public static final String DOCTOR_SELECT_ID_NULL = "A doctor should be selected";
    public static final String NOT_FOUND_ANIMAL = "Animal not found";
    public static final String NOT_FOUND_DOCTOR = "Doctor not found";
    public static final String NOT_FOUND_CUSTOMER = "Customer not found";
    public static final String NOT_FOUND_APPOINTMENT = "Appointment not found";
}

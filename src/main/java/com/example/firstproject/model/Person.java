package com.example.firstproject.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {

    private int id;

    @NotEmpty(message = "Your full name is empty")
    @Pattern(regexp = "(?=.{1,50}$)[A-Z][a-zA-Z]* [A-Z][a-zA-Z]*$", message = "Your name should be in the following format: Name Surname")
    private String fullName;
    @NotEmpty(message = "Fill your birth date")
    @Pattern(regexp = "(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19\\d{2}|20\\d{2})$", message = "Date Should in in this format: MM/DD/YYYY")
    private String birthDate;

    public Person(String fullName, String birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}

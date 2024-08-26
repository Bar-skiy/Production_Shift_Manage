package ru.bar.springs.models;

import jakarta.validation.constraints.*;

public class Person {

    private int id;

    @NotEmpty(message = "The field cannot be empty!")
    @Size(min = 2, max = 30, message = "The number of characters in a name can be from 2 to 30!")
    private String name;

    @Min(value = 0, message = "Age must be greater than 0!")
    @Max(value = 200, message = "Age must be less than 200!")
    private int age;

    @NotEmpty(message = "The field cannot be empty!")
    @Email(message = "Email must be here!")
    private String email;

    public Person() {}

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


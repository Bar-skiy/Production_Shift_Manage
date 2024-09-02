package ru.bar.springs.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Employee {

    private int employee_id;

    @NotEmpty(message = "The field cannot be empty!")
    @Size(min = 2, max = 100, message = "The number of characters in a name can be from 2 to 100!")
    public String employee_name;

    @Min(value = 1901, message = "Birth year must be greater than 1900!")
    @Max(value = 2029, message = "Birth year be less than 2030!")
    public int birth_year;

    @NotEmpty(message = "The field cannot be empty!")
    @Size(min = 2, max = 100, message = "The number of characters in a function can be from 2 to 100!")
    public String function;

    public Employee() {
    }

    public Employee(int employee_id, String employee_name, int birth_year, String function) {
        this.employee_id = employee_id;

        this.employee_name = employee_name;
        this.birth_year = birth_year;
        this.function = function;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employee_name='" + employee_name + '\'' +
                ", birth_year=" + birth_year +
                ", function='" + function + '\'' +
                '}';
    }
}

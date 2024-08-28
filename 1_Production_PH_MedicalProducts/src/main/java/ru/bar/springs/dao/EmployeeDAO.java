package ru.bar.springs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bar.springs.models.Employee;

import java.util.List;

@Component
public class EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> AllSelectInDao() {
        return jdbcTemplate.query("SELECT * FROM Employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee itemSelectInDao(int employee_id) {
        return jdbcTemplate.query("SELECT * FROM Employee WHERE employee_id=?", new Object[]{employee_id},
                new BeanPropertyRowMapper<>(Employee.class)).stream().findAny().orElse(null);
    }

    public void saveInDao(Employee employee) {
        jdbcTemplate.update("INSERT INTO Employee (employee_name, birth_year, function) VALUES (?, ?, ?)",
                employee.getEmployee_name(), employee.getBirth_year(), employee.getFunction());
    }

    public void deleteInDao(int employee_id) {
        jdbcTemplate.update("DELETE FROM Employee WHERE employee_id=?", employee_id);
    }

    public void updateInDao(int employee_id, Employee employee) {
        jdbcTemplate.update("UPDATE Employee SET employee_name=?, birth_year=?, function=? WHERE employee_id=?",
                employee.getEmployee_name(), employee.getBirth_year(), employee.getFunction(), employee_id);
    }

}

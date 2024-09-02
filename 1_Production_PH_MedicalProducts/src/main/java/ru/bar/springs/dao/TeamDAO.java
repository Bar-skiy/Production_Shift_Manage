package ru.bar.springs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bar.springs.models.Employee;
import ru.bar.springs.models.Team;

import java.util.List;

@Component
public class TeamDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Team> AllSelectInDao() {
        List<Team> all =
                jdbcTemplate.query("SELECT * FROM Team", new BeanPropertyRowMapper<>(Team.class));
         if (all.isEmpty()) {return null;}
         return all;
    }

    public Team itemSelectNameInDao(String name_leader) {
        return jdbcTemplate.query("SELECT * FROM Team WHERE name_leader=?", new Object[]{name_leader},
                new BeanPropertyRowMapper<>(Team.class)).stream().findAny().orElse(null);
    }

    public Team itemSelectIdentifierInDao(int identifier) {
        return jdbcTemplate.query("SELECT * FROM Team WHERE identifier=?", new Object[]{identifier},
                new BeanPropertyRowMapper<>(Team.class)).stream().findAny().orElse(null);
    }

    public Team itemSelectInDao(int team_id) {
        return jdbcTemplate.query("SELECT * FROM Team WHERE team_id=?", new Object[]{team_id},
                new BeanPropertyRowMapper<>(Team.class)).stream().findAny().orElse(null);
    }

    public List<Employee> getEmployeesInDao(int team_id) {
        List<Employee> empl = jdbcTemplate.query("SELECT  employee_name, birth_year, function FROM team" +
                " JOIN employee ON team.team_id = employee.team_id where employee.team_id = ?",
                new BeanPropertyRowMapper<>(Employee.class), team_id);
        if (empl.isEmpty()) {return null;}
        return empl;
    }

    public void saveInDao(Team team) {
        jdbcTemplate.update("INSERT INTO Team (name_leader, identifier) VALUES (?, ?)",
                team.getName_leader(), team.getIdentifier());
    }

    public void deleteInDao(int team_id) {
        jdbcTemplate.update("DELETE FROM Team WHERE team_id=?", team_id);
    }

    public void updateInDao(int team_id, Team team) {
        jdbcTemplate.update("UPDATE Team SET name_leader=?, identifier=? WHERE team_id=?",
                team.getName_leader(), team.getIdentifier(), team_id);
    }
}

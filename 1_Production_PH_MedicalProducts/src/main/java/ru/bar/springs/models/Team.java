package ru.bar.springs.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Team {

    private int team_id;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(min = 2, max = 100, message = "Количество строк должно быть больше 2 и меньше 100")
    private String name_leader;


    @Min(value = 1001, message = "Номер должен быть больше 1000")
    @Max(value = 5009, message = "Номер должен быть меньше 5010!")
    private int identifier;

    public Team() {
    }

    public Team(int team_id, String name_leader, int identifier) {
        this.team_id = team_id;
        this.name_leader = name_leader;
        this.identifier = identifier;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getName_leader() {
        return name_leader;
    }

    public void setName_leader(String name_leader) {
        this.name_leader = name_leader;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "Team{" +
                "team_id=" + team_id +
                ", name='" + name_leader + '\'' +
                ", identifier=" + identifier +
                '}';
    }
}

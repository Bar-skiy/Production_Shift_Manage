package ru.bar.springs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bar.springs.dao.TeamDAO;
import ru.bar.springs.models.Team;

@Component
public class TeamValidator implements Validator {

    private final TeamDAO teamDAO;

    @Autowired
    public TeamValidator(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Team.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Team team = (Team) target;

        if (teamDAO.itemSelectNameInDao(team.getName_leader()) != null)
            errors.rejectValue("name_leader", "Leader.exists",
                    "Лидер команды с таким ФИО уже существует");

        if (teamDAO.itemSelectIdentifierInDao(team.getIdentifier()) != null)
            errors.rejectValue("identifier", "Identifier.exists",
                    "Идентификатор команды уже существует");
    }
}

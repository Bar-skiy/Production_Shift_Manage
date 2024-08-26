package ru.bar.springs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bar.springs.dao.TeamDAO;

@Controller
@RequestMapping("/teams")
public class TeamsController {

    private final TeamDAO teamDAO;

    //Внедряем personDAO в конструктор class PeopleController
    @Autowired
    public TeamsController(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    //Отображение всех записей
    //название метода не важно, ни где явно не используется, @GetMapping нужен для выполнения метода по запросу по определенному адресу из браузера
    @GetMapping
    public String allInController(Model model) {
        // Получим всех людей из DAO и передадим на отображение в представление
        System.out.println(model.addAttribute("teams", teamDAO.allInDao()));
        return "teams/all";
    }

    //отображение одной записи
    //@PathVariable - извлекает id из запроса
    @GetMapping("/{team_id}")
    public String selectedInController(@PathVariable("team_id") int team_id, Model model) {
        // Получим одного человека из DAO и передадим на отображение в представление
        model.addAttribute("team", teamDAO.selectedInDao(team_id));
        return "teams/selected";

    }
}

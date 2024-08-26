package ru.bar.springs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bar.springs.dao.TeamDAO;

import java.util.Arrays;

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
    public String indexInController(Model model) {
        // Получим всех людей из DAO и передадим на отображение в представление
        System.out.println(model.addAttribute("teams", teamDAO.indexInDao()));
        return "teams/index";
    }
}

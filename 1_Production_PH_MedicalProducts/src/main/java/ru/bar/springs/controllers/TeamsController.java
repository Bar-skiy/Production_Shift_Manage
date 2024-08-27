package ru.bar.springs.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bar.springs.dao.TeamDAO;
import ru.bar.springs.models.Team;

@Controller
@RequestMapping("/teams")
public class TeamsController {

    private final TeamDAO teamDAO;

    @Autowired
    public TeamsController(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    //Отображение всех записей
    @GetMapping
    public String allInController(Model model) {
        // Получим всех людей из DAO и передадим на отображение в представление
        System.out.println(model.addAttribute("teams", teamDAO.allInDao()));
        return "teams/all";
    }

    //отображение одной записи
    @GetMapping("/{team_id}")
    public String selectedInController(@PathVariable("team_id") int team_id, Model model) {
        // Получим одного человека из DAO и передадим на отображение в представление
        model.addAttribute("team", teamDAO.selectedInDao(team_id));
        return "teams/selected";
    }

    //Отображение формы создания новой записи
    @GetMapping("/new")
    public String newPersonInController(@ModelAttribute("team_new") Team team) {
        //Создаем объект с пустым конструктором и передадим его для Thymeleaf template
        return "teams/new";
    }

    //Создание новой записи
    @PostMapping
    public String createInController(
            @ModelAttribute("team_new") @Valid Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teams/new";
        }
        teamDAO.saveInDao(team);
        return "redirect:/teams";
    }


}

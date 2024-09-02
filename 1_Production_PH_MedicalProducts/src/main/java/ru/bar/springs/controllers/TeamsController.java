package ru.bar.springs.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bar.springs.dao.TeamDAO;
import ru.bar.springs.models.Team;
import ru.bar.springs.util.TeamValidator;

@Controller
@RequestMapping("/teams")
public class TeamsController {

    private final TeamDAO teamDAO;
    private final TeamValidator teamValidator;

    @Autowired
    public TeamsController(TeamDAO teamDAO, TeamValidator teamValidator) {
        this.teamDAO = teamDAO;
        this.teamValidator = teamValidator;
    }

    // Отображение всех записей
    @GetMapping
    public String allShowInController(Model model) {
        model.addAttribute("teams", teamDAO.AllSelectInDao());
        return "teams/all";
    }

    //отображение одной записи
    @GetMapping("/{team_id}")
    public String itemShowInController(@PathVariable("team_id") int team_id, Model model) {
        model.addAttribute("team", teamDAO.itemSelectInDao(team_id));
        // для показа листа сотрудников
       model.addAttribute("empl_list_team", teamDAO.getEmployeesInDao(team_id));
        return "teams/item";
    }

    // Отображение формы создания новой записи
    @GetMapping("/new")
    public String formCreateInController(@ModelAttribute("team_new") Team team) {
        return "teams/new";
    }

    // Создание новой записи
    @PostMapping
    public String createInController(
            @ModelAttribute("team_new") @Valid Team team, BindingResult bindingResult) {
        teamValidator.validate(team, bindingResult);

        if (bindingResult.hasErrors()) {
            return "teams/new";
        }
        teamDAO.saveInDao(team);
        return "redirect:/teams";
    }

    // Удаление записи
    @DeleteMapping("/{team_id}")
    public String deleteInController(@PathVariable("team_id") int team_id) {
        teamDAO.deleteInDao(team_id);
        return "redirect:/teams";
    }

    // Отображение формы изменения записи
    @GetMapping("/{team_id}/edit")
    public String formEditInController(Model model, @PathVariable("team_id") int team_id) {
        model.addAttribute("team_edit", teamDAO.itemSelectInDao(team_id));
        return "teams/edit";
    }

    // Обновление записи
    @PatchMapping("/{team_id}")
    public String updateInController(@ModelAttribute("team_edit") @Valid Team team,
                                     BindingResult bindingResult,
                                     @PathVariable("team_id") int team_id) {
//        teamValidator.validate(team, bindingResult);

        if (bindingResult.hasErrors())
            return "teams/edit";

        teamDAO.updateInDao(team_id, team);
        return "redirect:/teams";
    }

}

package ru.bar.springs.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bar.springs.dao.EmployeeDAO;
import ru.bar.springs.dao.TeamDAO;
import ru.bar.springs.models.Employee;
import ru.bar.springs.models.Team;

import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeDAO employeeDAO;
    private final TeamDAO teamDAO;

    public EmployeeController(EmployeeDAO employeeDAO, TeamDAO teamDAO) {
        this.employeeDAO = employeeDAO;
        this.teamDAO = teamDAO;
    }

    // Отображение всех записей
    @GetMapping
    public String allShowInController(Model model) {
        model.addAttribute("employees", employeeDAO.AllSelectInDao());
        return "employees/all";
    }

    //отображение одной записи
    @GetMapping("/{employee_id}")
    public String itemShowInController(Model model, @PathVariable("employee_id") int employee_id,
                                       @ModelAttribute("team") Team team) {
        model.addAttribute("employee", employeeDAO.itemSelectInDao(employee_id));

        // получение данных со всех колонок команды сотрудника
        Optional<Team> member = employeeDAO.getIdentInDao(employee_id);

        if (member.isPresent()) {
            // передача в th данных со всех колонок команды сотрудника
            model.addAttribute("member", member.get());
        } else {
            // передача в th данных для отображения выпадающего списка производственных команд
            model.addAttribute("teams", teamDAO.AllSelectInDao());
        }
        return "employees/item";
    }

    //Прикрепление сотрудника к команде
    @PatchMapping("/assign/{employee_id}")
    public String assignInController(@PathVariable("employee_id") int employee_id,
                                     @ModelAttribute("team_id_th") Team selectedTeam) {
        employeeDAO.assignInDao(employee_id, selectedTeam);
        return "redirect:/employees/" + employee_id;
    }

    // Открепление сотрудника от команды
    @PatchMapping("/unpin/{employee_id}")
    public String unpinInController(@PathVariable("employee_id") int employee_id) {
        employeeDAO.unpinInDao(employee_id);
        return "redirect:/employees/" + employee_id;
    }

    // Отображение формы создания новой записи
    @GetMapping("/new")
    public String formCreateInController(@ModelAttribute("employee_new") Employee employee) {
        return "employees/new";
    }

    // Создание новой записи
    @PostMapping
    public String createInController(
            @ModelAttribute("employee_new") @Valid Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employees/new";
        }
        employeeDAO.saveInDao(employee);
        return "redirect:/employees";
    }

    // Удаление записи
    @DeleteMapping("/{employee_id}")
    public String deleteInController(@PathVariable("employee_id") int employee_id) {
        employeeDAO.deleteInDao(employee_id);
        return "redirect:/employees";
    }

    // Отображение формы изменения записи
    @GetMapping("/{employee_id}/edit")
    public String formEditInController(Model model, @PathVariable("employee_id") int employee_id) {
        model.addAttribute("employee_edit", employeeDAO.itemSelectInDao(employee_id));
        return "employees/edit";
    }

    // Обновление записи
    @PatchMapping("/{employee_id}")
    public String updateInController(@ModelAttribute("employee_edit") @Valid Employee employee,
                                     BindingResult bindingResult,
                                     @PathVariable("employee_id") int employee_id) {
        if (bindingResult.hasErrors())
            return "employees/edit";

        employeeDAO.updateInDao(employee_id, employee);
        return "redirect:/employees";
    }

}

package ru.bar.springs.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bar.springs.dao.PersonDAO;
import ru.bar.springs.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    //Внедряем personDAO в конструктор class PeopleController
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //Отображение всех записей
    //название метода не важно, ни где явно не используется, @GetMapping нужен для выполнения метода по запросу по определенному адресу из браузера
    @GetMapping
    public String indexInController(Model model) {
        // Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("peopl", personDAO.indexInDao());
        return "people/index";
    }

    //отображение одной записи
    //@PathVariable - извлекает id из запроса
    @GetMapping("/{id}")
    public String showInController(@PathVariable("id") int id, Model model) {
        // Получим одного человека из DAO и передадим на отображение в представление
        model.addAttribute("pers", personDAO.showInDao(id));
        return "people/show";
    }

    //Отображение формы создания новой записи
    @GetMapping("/new")
    public String newPersonInController(@ModelAttribute("person_new") Person person) {
        //Создаем объект с пустым конструктором и передадим его для Thymeleaf template
        return "people/new";
    }

    //Создание новой записи
    @PostMapping
    public String createInController(@ModelAttribute("person_new") @Valid Person person,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.saveInDao(person);
        return "redirect:/people";
    }

    ////Отображение формы изменения записи
    //@PathVariable - извлекает id из запроса
    @GetMapping("/{id}/edit")
    public String editInController(Model model, @PathVariable("id") int id) {
        model.addAttribute("person_edit", personDAO.showInDao(id));
        return "people/edit";
    }

    //Изменение записи
    //@ModelAttribute("person") Person person - принимаем объект person из формы
    @PatchMapping("/{id}")
    public String updateInController(@ModelAttribute("person_edit") @Valid Person person, BindingResult bindingResult,
                                     @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "people/edit";

        personDAO.updateInDao(id, person);
        return "redirect:/people";
    }

    //Удаление записи
    @DeleteMapping("/{id}")
    public String deleteInController(@PathVariable("id") int id) {
        personDAO.deleteInDao(id);
        return "redirect:/people";
    }

}

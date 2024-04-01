package com.example.librarymanager.controller;
import com.example.librarymanager.model.Person;
import com.example.librarymanager.services.PeopleService;
import com.example.librarymanager.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleService.showAll());
        return "person/people";
    }

    @GetMapping("/new")
    public String add(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("person", person);
        return "person/new";
    }

    @PostMapping()
    public String insert(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) return "person/new";
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        if (peopleService.find(id) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        Person person = peopleService.find(id);
        model.addAttribute("person", person);
        return "person/show";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.find(id));
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String change(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "person/edit";
        peopleService.update(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}

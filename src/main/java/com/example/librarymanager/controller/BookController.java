package com.example.librarymanager.controller;

import com.example.librarymanager.dao.BookDAO;
import com.example.librarymanager.dao.PersonDAO;
import com.example.librarymanager.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.showAll());
        return "book/books";
    }

    @GetMapping("/new")
    public String add(Model model, @ModelAttribute("book") Book book){
        model.addAttribute("book", book);
        return "book/new";
    }

    @PostMapping()
    public String insert(@ModelAttribute("book") Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "book/new";
        bookDAO.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        Book book = bookDAO.find(id);
        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.showAll());
        String assignedPerson = book.getPersonId() == null ? null : personDAO.find(book.getPersonId()).getFullName();
        model.addAttribute("assignedPerson", assignedPerson);
        return "book/show";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.find(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String change(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "book/edit";
        bookDAO.update(id, book);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @RequestParam("personId") int personId){
        Book book = bookDAO.find(id);
        book.setPersonId(personId);
        bookDAO.update(id, book);
        return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/freed")
    public String freed(@PathVariable("id") int id, Model model){
        Book book = bookDAO.find(id);
        book.setPersonId(null);
        bookDAO.update(id, book);
        return "redirect:/book/" + id;
    }

    @DeleteMapping("/{id}")
    public String removePerson(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/book";
    }
}

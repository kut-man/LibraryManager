package com.example.librarymanager.controller;

import com.example.librarymanager.model.Book;
import com.example.librarymanager.services.BooksService;
import com.example.librarymanager.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(name = "page", required = false) Integer page,
                                  @RequestParam(name = "books_per_page", required = false) Integer perPage,
                                  @RequestParam(name = "sort_by_year", required = false) Boolean asc) {
        model.addAttribute("books", booksService.showAll(page, perPage, asc));
        model.addAttribute("asc", asc);
        return "book/books";
    }

    @GetMapping("/new")
    public String add(Model model, @ModelAttribute("book") Book book) {
        model.addAttribute("book", book);
        return "book/new";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "query", required = false) String query) {
        if (query != null) {
            model.addAttribute("books", booksService.searchByName(query));
        }
        else {
            model.addAttribute("books", booksService.showAll());
        }

        return "book/search";
    }

    @PostMapping()
    public String insert(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "book/new";
        booksService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        Book book = booksService.find(id);
        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.showAll());
        String assignedPerson = book.getOwner() == null ? null : book.getOwner().getFullName();
        model.addAttribute("assignedPerson", assignedPerson);
        return "book/show";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.find(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String change(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "book/edit";
        booksService.update(book);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @RequestParam("owner") int owner) {
        Book book = booksService.find(id);
        book.setOwner(peopleService.find(owner));
        booksService.update(book);
        return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/freed")
    public String freed(@PathVariable("id") int id, Model model) {
        Book book = booksService.find(id);
        book.setOwner(null);
        booksService.update(book);
        return "redirect:/book/" + id;
    }

    @DeleteMapping("/{id}")
    public String removePerson(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/book";
    }
}

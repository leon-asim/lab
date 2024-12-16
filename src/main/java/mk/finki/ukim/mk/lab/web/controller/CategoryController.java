package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add-form")
    public String addCategoryForm() {

        return "add-category";
    }

    @PostMapping("/add")
    public String addCategory(@RequestParam String categoryName) {
        categoryService.save(categoryName);

        return "redirect:/events";
    }
}

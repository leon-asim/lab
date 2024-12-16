package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.repository.CategoryRepository;
import mk.finki.ukim.mk.lab.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Optional<Category> save(String name) {
//        categoryRepository.findAll().removeIf(i -> i.getName().equals(name));

        categoryRepository.deleteByName(name);
        Category category = new Category(name);
        return Optional.of(categoryRepository.save(category));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}

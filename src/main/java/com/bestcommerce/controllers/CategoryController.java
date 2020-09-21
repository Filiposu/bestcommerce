package com.bestcommerce.controllers;

import com.bestcommerce.entities.Category;
import com.bestcommerce.entities.Product;
import com.bestcommerce.repository.CategoryRepository;
import com.bestcommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> categoryList() {
        return categoryService.listCategories();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public void createCategory() {

        Category category = new Category();
        category.setName("New category");
        categoryRepository.save(category);
    }


}


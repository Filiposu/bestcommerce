package com.bestcommerce.services.impl;

import com.bestcommerce.entities.Category;
import com.bestcommerce.repository.CategoryRepository;
import com.bestcommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public List<Category> listCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories;
    }
}

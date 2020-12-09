package com.mvc.service.impl;

import com.mvc.entity.CategoryEntity;
import com.mvc.repository.CategoryRepository;
import com.mvc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity item : entities) {
            result.put(item.getCode(), item.getName());
        }
        return result;
    }

    @Override
    public List<String> loadMenu() {
        List<String> menu = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity item : entities) {
            menu.add(item.getName());
        }
        return menu;
    }


}

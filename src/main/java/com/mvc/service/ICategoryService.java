package com.mvc.service;


import java.util.List;
import java.util.Map;

public interface ICategoryService {

    Map<String, String> findAll();

    List<String> loadMenu();

}

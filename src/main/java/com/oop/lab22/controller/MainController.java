package com.oop.lab22.controller;

import com.oop.lab22.Vacancy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    /*
    Метод зіставляє запити HTTP GET з певними методами оброблювача
    Створює лист, елементи якого - результат методу toString об'єктів класу Vacancy
    Записує лист у модель
    Повертає сторінку homePage
     */
    @GetMapping("/")
    public String mainPage(Model model){
        Vacancy vacancy= new Vacancy();
        List<String> v = new ArrayList<>();
        for(int i =0; i<vacancy.getAll().size(); i++){
            v.add(vacancy.getAll().get(i).toString());
        }
        model.addAttribute("vacancy", v);
        return "homePage";
    }

    /*
    Метод зіставляє запити HTTP GET з певними методами оброблювача
    Приймає параметри RequestParam String name, @RequestParam String description, @RequestParam String required_experience
            , @RequestParam boolean diploma, @RequestParam double salary
     Додає вакнасію у базу даних
     Перезавантажує сторінку
     */

    @PostMapping("/")
    public String addVacancy(@RequestParam String name, @RequestParam String description, @RequestParam String required_experience
            , @RequestParam boolean diploma, @RequestParam double salary){

        Vacancy vacancy = new Vacancy();
        vacancy.addVacancy(name, description, required_experience, diploma, salary);
        return "redirect:/";
    }
}

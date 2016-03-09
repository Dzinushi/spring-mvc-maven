package com.about.java.controllers;

import com.about.java.dto.TreeDTO;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.SaveService;
import com.about.java.service.interfaces.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private TreeService treeService;

    @Autowired
    private SaveService saveService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainpage() {

        // Создание тестовых данных
        try {
            saveService.addStaticCare();
            saveService.addStaticTree();
            saveService.addStaticPoison();
            saveService.addStaticPest();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        // Получение всех видов деревьев из таблицы
        ModelAndView mav = new ModelAndView("main");

        List<TreeDTO> treeDTOs = null;
        try {
            treeDTOs = treeService.get();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        mav.addObject("trees", treeDTOs);
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "id") Long id){

        try {
            treeService.delete(id);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return "redirect:";
    }
}
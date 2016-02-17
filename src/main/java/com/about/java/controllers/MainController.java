package com.about.java.controllers;

import com.about.java.dto.TreeDTO;
import com.about.java.service.exceptions.NoSuchObjectException;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainpage() {
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

//        try {
//            treeTypesService.deleteInvitation(id);
//        } catch (NoSuchObjectException e) {
//            e.printStackTrace();
//        }

        return "redirect:";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String edit(@RequestParam(value = "id") Long id,
                       @RequestParam(value = "type") String type){

        //reeTypesService.updateTree(new Tree(id, type));
        return "redirect:";
    }

    @RequestMapping(value = "/add")
    public void add(){

    }

    @RequestMapping(value = "/newTree", method = RequestMethod.POST)
    public String add(@RequestParam(value = "id") Long id,
                      @RequestParam(value = "type") String type){

//        try {
//            treeTypesService.add(new Tree(id, type));
//        } catch (ObjectAlreadyExistsException e) {
//            e.printStackTrace();
//        }

        return "redirect:";
    }
}
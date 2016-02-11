package com.about.java.controllers;

import com.about.java.models.trees.TreeTypes;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.TreeSubtypesService;
import com.about.java.service.interfaces.TreeTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private TreeTypesService treeTypesService;

    @Autowired
    private TreeSubtypesService treeSubtypesService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainpage() {
        ModelAndView mav = new ModelAndView("main");

        List<TreeTypes> treeTypes = new ArrayList<TreeTypes>();
        treeTypes = treeTypesService.getAllTree();

//        List<TreeSubtypes> treeSubtypes = new ArrayList<TreeSubtypes>();
//        treeSubtypes = treeSubtypesService.getAllTree();

        mav.addObject("treeTypes", treeTypes);
//        mav.addObject("treeSubtypes", treeSubtypes);
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "id") Long id){

        try {
            treeTypesService.deleteInvitation(id);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return "redirect:";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String edit(@RequestParam(value = "id") Long id,
                       @RequestParam(value = "type") String type){

        //reeTypesService.updateTree(new TreeTypes(id, type));
        return "redirect:";
    }

    @RequestMapping(value = "/add")
    public void add(){

    }

    @RequestMapping(value = "/newTree", method = RequestMethod.POST)
    public String add(@RequestParam(value = "id") Long id,
                      @RequestParam(value = "type") String type){

        try {
            treeTypesService.add(new TreeTypes(id, type));
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        return "redirect:";
    }
}
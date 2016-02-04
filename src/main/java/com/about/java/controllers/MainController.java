package com.about.java.controllers;

import com.about.java.models.Tree;
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

        List<Tree> trees;
        trees = treeService.getAllTree();
        mav.addObject("trees", trees);
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "id") Long id){

        try {
            treeService.deleteInvitation(id);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return "redirect:";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestParam(value = "id") Long id,
                       @RequestParam(value = "type") String type){

        return "redirect:";
    }

    @RequestMapping(value = "/add")
    public void add(){

    }
}
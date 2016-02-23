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

@Controller
public class TreeManagerController {

    @Autowired
    private TreeService treeService;

    @RequestMapping(value = "details/detailsTree", method = RequestMethod.GET)
    public ModelAndView details(@RequestParam(value = "id") long id){
        ModelAndView mav = new ModelAndView("details/detailsTree");
        TreeDTO treeDTO = null;
        try {
            treeDTO = treeService.get(id);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        mav.addObject("tree", treeDTO);
        return mav;
    }

    @RequestMapping(value = "details/updateTree", method = RequestMethod.POST)
    public String update(){
        return "redirect:";
    }
}

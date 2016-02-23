package com.about.java.controllers;

import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.interfaces.PestService;
import com.about.java.service.interfaces.PoisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class PoisonController {

    @Autowired
    private PoisonService poisonService;

    @Autowired
    private PestService pestService;

    @RequestMapping(value = "details/detailsPoison", method = RequestMethod.GET)
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView("details/detailsPoisons");
        try {
            List<PoisonDTO> poisonDTOs = poisonService.get();
            mav.addObject("poisons", poisonDTOs);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "add/addPoison", method = RequestMethod.POST)
    public ModelAndView add(){
        ModelAndView mav = new ModelAndView("add/addPoison");
        try {
            List<PestDTO> pestDTOs = pestService.get();
            mav.addObject("pests", pestDTOs);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(value = "details/applyPoisons", method = RequestMethod.POST)
    public String apply(){
        return "redirect:../add/addTree";
    }
}

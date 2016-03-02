package com.about.java.controllers;


import com.about.java.dto.PestDTO;
import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PestContoller {

    @Autowired
    private PestService pestService;

    @RequestMapping(value = "details/detailsPests", method = RequestMethod.GET)
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView("details/detailsPests");
        try {
            List<PestDTO> pestDTOList = pestService.get();
            mav.addObject("pests", pestDTOList);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(value = "add/addPest", method = RequestMethod.POST)
    public void add(){

    }

    @RequestMapping(value = "details/detailsPests", method = RequestMethod.POST)
    public String add(@RequestParam(value = "name") String name, ModelMap modelMap) throws ObjectAlreadyExistsException {
        Pest pest = new Pest();
        pest.setName(name);
        pestService.add(pest);

        try {
            List<PestDTO> pestDTOList = pestService.get();
            modelMap.addAttribute("pests", pestDTOList);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return "details/detailsPests";
    }

    @RequestMapping(value = "details/applyPests")
    public String apply(@RequestParam(value = "checkedPests") List<Long> idPests, ModelMap modelMap) throws NoSuchObjectException {
        List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
        for (Long idPest : idPests) {
            PestDTO pest = pestService.get(idPest);
            pestDTOs.add(pest);
        }
        modelMap.addAttribute("pests", pestDTOs);
        return "add/addPoison";
    }
}
package com.about.java.controllers;


import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonPestDTO;
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
import java.util.Set;

@Controller
public class PestContoller {

    @Autowired
    private PestService pestService;

    @RequestMapping(value = "details/detailsPests", method = RequestMethod.GET)
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView("details/detailsPests");
        try {
            Set<PestDTO> pestDTOList = pestService.getAll();
            mav.addObject("pests", pestDTOList);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(value = "add/addPest", method = RequestMethod.POST)
    public void add(){

    }

    @RequestMapping(value = "delete/deletePest", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "id") Long id) throws NoSuchObjectException {
        pestService.delete(id);
        return "redirect:../details/detailsPests";
    }

    @RequestMapping(value = "details/detailsPests", method = RequestMethod.POST)
    public String add(@RequestParam(value = "name") String name, ModelMap modelMap) throws ObjectAlreadyExistsException {
        List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();
        PoisonPestDTO poisonPestDTO = new PoisonPestDTO();
        PestDTO pestDTO = new PestDTO();
        pestDTO.setName(name);
        poisonPestDTO.setPestDTO(pestDTO);
        poisonPestDTOs.add(poisonPestDTO);
        pestService.add(poisonPestDTOs);

        try {
            Set<PestDTO> pestDTOList = pestService.getAll();
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
            PestDTO pest = pestService.getByID(idPest);
            pestDTOs.add(pest);
        }
        modelMap.addAttribute("pests", pestDTOs);
        return "add/addPoison";
    }
}
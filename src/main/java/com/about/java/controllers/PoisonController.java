package com.about.java.controllers;

import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
import com.about.java.dto.PoisonPestDTO;
import com.about.java.dto.TreePoisonDTO;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PestService;
import com.about.java.service.interfaces.PoisonService;
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
public class PoisonController {

    @Autowired
    private PoisonService poisonService;

    @Autowired
    private PestService pestService;

    @RequestMapping(value = "details/detailsPoisons", method = RequestMethod.GET)
    public ModelAndView get(){
        ModelAndView mav = new ModelAndView("details/detailsPoisons");
        try {
            List<PoisonDTO> poisonDTOs = poisonService.getAll();
            mav.addObject("poisons", poisonDTOs);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "add/addPoison", method = RequestMethod.POST)
    public void add(){

    }

    @RequestMapping(value = "delete/deletePoison", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "id") Long id) throws NoSuchObjectException {
        poisonService.delete(id);
        return "redirect:../details/detailsPoisons";
    }

    @RequestMapping(value = "details/detailsPoisons", method = RequestMethod.POST)
    public String add(@RequestParam("name") String name,
                      @RequestParam("idPests") List<Long> idPests,
                      ModelMap modelMap) throws NoSuchObjectException {
        PoisonDTO poisonDTO = new PoisonDTO();
        poisonDTO.setName(name);

        List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();
        for (Long idPest : idPests) {
            PoisonPestDTO poisonPestDTO = new PoisonPestDTO();
            poisonPestDTO.setPoisonDTO(poisonDTO);
            PestDTO pestDTO = pestService.getByID(idPest);
            poisonPestDTO.setPestDTO(pestDTO);
            poisonPestDTOs.add(poisonPestDTO);
        }

        try {
            poisonService.add(new ArrayList<TreePoisonDTO>(), poisonPestDTOs);
            List<PoisonDTO> poisonDTOs = poisonService.getAll();
            modelMap.addAttribute("poisons", poisonDTOs);
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        return "details/detailsPoisons";
    }

    @RequestMapping(value = "details/applyPoisons")
    public String apply(@RequestParam(value = "checkedPoisons") List<Long> idPoisons, ModelMap modelMap) throws NoSuchObjectException {
        List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
        for (Long idPoison : idPoisons) {
            PoisonDTO poisonDTO = poisonService.getByID(idPoison);
            poisonDTOs.add(poisonDTO);
        }
        modelMap.addAttribute("poisons", poisonDTOs);
        return "add/addTree";
    }
}
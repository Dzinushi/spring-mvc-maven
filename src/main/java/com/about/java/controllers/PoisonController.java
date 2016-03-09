package com.about.java.controllers;

import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
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
            List<PoisonDTO> poisonDTOs = poisonService.get();
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
        PoisonDTO poison = new PoisonDTO();
        poison.setName(name);

        List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
        for (Long idPest : idPests) {
            PestDTO pestDTO = pestService.get(idPest);
            pestDTOs.add(pestDTO);
        }

        poison.setPestDTOs(pestDTOs);

        try {
            poisonService.add(poison);
            List<PoisonDTO> poisonDTOs = poisonService.get();
            modelMap.addAttribute("poisons", poisonDTOs);
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        return "details/detailsPoisons";
    }

    @RequestMapping(value = "details/applyPoisons")
    public String apply(@RequestParam(value = "checkedPoisons") List<Long> idPoisons, ModelMap modelMap) throws NoSuchObjectException {
        List<PoisonDTO> pestDTOs = new ArrayList<PoisonDTO>();
        for (Long idPoison : idPoisons) {
            PoisonDTO poisonDTO = poisonService.get(idPoison);
            pestDTOs.add(poisonDTO);
        }
        modelMap.addAttribute("poisons", pestDTOs);
        return "add/addTree";
    }
}
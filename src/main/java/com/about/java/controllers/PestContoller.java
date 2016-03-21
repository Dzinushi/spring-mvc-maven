package com.about.java.controllers;


import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
import com.about.java.dto.PoisonPestDTO;
import com.about.java.models.Pest;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PestService;
import com.about.java.service.interfaces.PoisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private PoisonService poisonService;

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
    public String apply(@RequestParam(value = "pests") List<String> idPests, ModelMap modelMap) throws NoSuchObjectException {
        List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
        List<Long> ids = new ArrayList<Long>();
        for (String idPest : idPests) {
            idPest = idPest.replaceAll("\\D", "");
            ids.add(Long.valueOf(idPest));
        }
        for (Long id : ids) {
            PestDTO pest = pestService.getByID(id);
            pestDTOs.add(pest);
        }
        modelMap.addAttribute("pests", pestDTOs);
        return "add/addPoison";
    }

    @RequestMapping(value = "update/updatePest")
    public ModelAndView update(@RequestParam(value = "id") Long id) throws NoSuchObjectException {
        List<PoisonPestDTO> pestPoisonDTOs = pestService.get(id);

        List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
        for (PoisonPestDTO pestPoisonDTO : pestPoisonDTOs){
            PoisonDTO poisonDTO = pestPoisonDTO.getPoisonDTO();
            poisonDTOs.add(poisonDTO);
        }

        PestDTO pestDTO = pestPoisonDTOs.get(0).getPestDTO();
        pestDTO.setPoisonDTOs(poisonDTOs);

        ModelAndView mav = new ModelAndView("update/updatePest");
        mav.addObject("pest", pestDTO);

        List<PoisonDTO> allPoisons = poisonService.getAll();
        mav.addObject("allPoisons", allPoisons);

        return mav;
    }

    @RequestMapping(value = "applyUpdate")
    public void update(@RequestParam(value = "id") Long id,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "poisonNames") List<String> poisons){
        List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();
        for (String poison : poisons) {
            PoisonPestDTO poisonPestDTO = new PoisonPestDTO();

            PestDTO pestDTO = new PestDTO();
            pestDTO.setId(id);
            pestDTO.setName(name);
            poisonPestDTO.setPestDTO(pestDTO);

            PoisonDTO poisonDTO = poisonService.getByName(poison);
            poisonPestDTO.setPoisonDTO(poisonDTO);

            poisonPestDTOs.add(poisonPestDTO);
        }

        try {
            pestService.update(poisonPestDTOs);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
}
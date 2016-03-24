package com.about.java.controllers;

import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
import com.about.java.dto.PoisonPestDTO;
import com.about.java.dto.TreePoisonDTO;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.PestService;
import com.about.java.service.interfaces.PoisonService;
import com.about.java.service.interfaces.TreeService;
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

    @RequestMapping(value = "add/newPoison", method = RequestMethod.POST)
    public String add(@RequestParam("name") String name,
                      @RequestParam("idPests") List<Long> idPests) throws NoSuchObjectException {
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
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        return "redirect:../details/detailsPoisons";
    }

    @RequestMapping(value = "update/updatePoison", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam(value = "id") Long id){
        PoisonDTO poisonDTO = null;
        try {
            poisonDTO = poisonService.getByID(id);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        Set<PestDTO> pestDTOs = null;
        try {
            pestDTOs = pestService.getAll();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("update/updatePoison");
        mav.addObject("poison", poisonDTO);
        mav.addObject("allPests", pestDTOs);

        return mav;
    }

    @RequestMapping(value = "update/updatePoison/applyUpdate")
    public String update(@RequestParam(value = "id") Long id,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "pests") List<String> pests){

        // создаем новые наборы данных между сущностями PoisonDTO и PestDTO
        List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();
        for (String pest : pests) {
            PoisonPestDTO poisonPestDTO = new PoisonPestDTO();

            PoisonDTO poisonDTO = new PoisonDTO();
            poisonDTO.setId(id);
            poisonDTO.setName(name);
            poisonPestDTO.setPoisonDTO(poisonDTO);

            PestDTO pestDTO = pestService.getByName(pest);
            poisonPestDTO.setPestDTO(pestDTO);

            poisonPestDTOs.add(poisonPestDTO);
        }

        // формируем PoisonDTO-объект со связями на объекты PestDTO
        PoisonDTO poisonDTO = new PoisonDTO();
        poisonDTO.setId(id);
        poisonDTO.setName(name);
        List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
        for (String pest1 : pests) {
            PestDTO pestDTO = new PestDTO();
            pestDTO.setName(pest1);
            pestDTOs.add(pestDTO);
        }
        poisonDTO.setPestDTOs(pestDTOs);

        // получаем наборы данных между сущностями TreeDTO и PoisonDTO
        List<TreePoisonDTO> treePoisonDTOs = null;
        try {
            treePoisonDTOs = poisonService.getTreePoisons(id);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
        assert treePoisonDTOs != null;
        for (TreePoisonDTO treePoisonDTO : treePoisonDTOs) {
            treePoisonDTO.setPoisonDTO(poisonDTO);
        }

        try {
            poisonService.update(treePoisonDTOs, poisonPestDTOs);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return "redirect:../../details/detailsPoisons";
    }

    @RequestMapping(value = "details/applyPoisons")
    public String apply(@RequestParam(value = "poisons") List<Long> idPoisons, ModelMap modelMap) throws NoSuchObjectException {
        List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
        for (Long idPoison : idPoisons) {
            PoisonDTO poisonDTO = poisonService.getByID(idPoison);
            poisonDTOs.add(poisonDTO);
        }
        modelMap.addAttribute("poisons", poisonDTOs);
        return "add/addTree";
    }
}
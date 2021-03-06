package com.about.java.controllers;

import com.about.java.dto.*;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
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

@Controller
public class TreeManagerController {

    @Autowired
    private TreeService treeService;

    @Autowired
    private PoisonService poisonService;

    @RequestMapping(value = "details/detailsTree", method = RequestMethod.GET)
    public ModelAndView details(@RequestParam(value = "id") long id){
        ModelAndView mav = new ModelAndView("details/detailsTree");
        TreeDTO treeDTO = new TreeDTO();
        try {
            treeDTO = treeService.getByID(id);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        mav.addObject("tree", treeDTO);
        return mav;
    }

    @RequestMapping(value = "update/updateTree", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam(value = "id") Long id){
        TreeDTO treeDTO = null;
        List<PoisonDTO> poisonDTOs = null;
        try {
            treeDTO = treeService.getByID(id);
            poisonDTOs = poisonService.getAll();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("update/updateTree");
        mav.addObject("tree", treeDTO);
        mav.addObject("allPoisons", poisonDTOs);

        return mav;
    }

    @RequestMapping(value = "update/updateTree/applyUpdate", method = RequestMethod.POST)
    public String update(@RequestParam(value = "id") Long id,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "height") String height,
                         @RequestParam(value = "describe") String describe,
                         @RequestParam(value = "care_id") Long careId,
                         @RequestParam(value = "care") String care,
                         @RequestParam(value = "poisons") List<String> poisons){

        List<TreePoisonDTO> treePoisonDTOs = new ArrayList<TreePoisonDTO>();
        for (String poison : poisons) {
            TreePoisonDTO treePoisonDTO = new TreePoisonDTO();

            TreeDTO treeDTO = new TreeDTO();
            treeDTO.setId(id);
            treeDTO.setName(name);
            treeDTO.setHeight(height);
            treeDTO.setDescribe(describe);
            CareDTO careDTO = new CareDTO();
            careDTO.setId(careId);
            careDTO.setDescribe(care);
            treeDTO.setCareDTO(careDTO);

            PoisonDTO poisonDTO = poisonService.getByName(poison);

            treePoisonDTO.setTreeDTO(treeDTO);
            treePoisonDTO.setPoisonDTO(poisonDTO);
            treePoisonDTOs.add(treePoisonDTO);
        }

        try {
            treeService.update(treePoisonDTOs);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        return "redirect:../../";
    }

    @RequestMapping(value = "add/addTree")
    public void add(){
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String add(@RequestParam("name") String name,
                    @RequestParam("height") String height,
                    @RequestParam("describe") String describe,
                    @RequestParam("care") String care,
                    @RequestParam("idPoisons") List<Long> idPoisons,
                      ModelMap modelMap) throws NoSuchObjectException {
        TreeDTO treeDTO = new TreeDTO();
        treeDTO.setName(name);
        treeDTO.setHeight(height);
        treeDTO.setDescribe(describe);

        CareDTO careDTO = new CareDTO();
        careDTO.setDescribe(care);

        treeDTO.setCareDTO(careDTO);

        List<TreePoisonDTO> treePoisonDTOs = new ArrayList<TreePoisonDTO>();
        for (Long idPoison : idPoisons) {
            PoisonDTO poisonDTO = poisonService.getByID(idPoison);
            TreePoisonDTO treePoisonDTO = new TreePoisonDTO();
            treePoisonDTO.setTreeDTO(treeDTO);
            treePoisonDTO.setPoisonDTO(poisonDTO);
            treePoisonDTOs.add(treePoisonDTO);
        }

        try {
            treeService.add(treePoisonDTOs);
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }


        List<TreeDTO> treeDTOs = treeService.getAll();
        modelMap.addAttribute("trees", treeDTOs);

        return "main";
    }
}
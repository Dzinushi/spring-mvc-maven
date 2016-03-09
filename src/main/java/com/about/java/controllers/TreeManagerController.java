package com.about.java.controllers;

import com.about.java.dao.interfaces.CareDAO;
import com.about.java.dto.CareDTO;
import com.about.java.dto.PoisonDTO;
import com.about.java.dto.TreeDTO;
import com.about.java.models.Care;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.CareService;
import com.about.java.service.interfaces.PoisonService;
import com.about.java.service.interfaces.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TreeManagerController {

    @Autowired
    private CareService careService;

    @Autowired
    private TreeService treeService;

    @Autowired
    private PoisonService poisonService;

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

    @RequestMapping(value = "add/addTree")
    public void add(){
    }

    @RequestMapping(value = "add/applyTree", method = RequestMethod.POST)
    public void add(@RequestParam("name") String name,
                    @RequestParam("height") String height,
                    @RequestParam("describe") String describe,
                    @RequestParam("care") String care,
                    @RequestParam("idPoisons") List<Long> idPoisons) throws NoSuchObjectException {
        TreeDTO treeDTO = new TreeDTO();
        treeDTO.setName(name);
        treeDTO.setHeight(height);
        treeDTO.setDescribe(describe);

        CareDTO careDTO = new CareDTO();
        careDTO.setDescribe(care);
        try {
            Long idCare = careService.add(careDTO);
            careDTO.setId(idCare);
            treeDTO.setCareDTO(careDTO);
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
        for (Long idPoison : idPoisons) {
            PoisonDTO poisonDTO = poisonService.get(idPoison);
            poisonDTOs.add(poisonDTO);
        }
        treeDTO.setPoisonDTOs(poisonDTOs);

        try {
            treeService.add(treeDTO);
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}

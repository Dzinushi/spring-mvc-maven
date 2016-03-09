package com.about.java.service.implement;

import com.about.java.dto.CareDTO;
import com.about.java.dto.PestDTO;
import com.about.java.dto.PoisonDTO;
import com.about.java.dto.TreeDTO;
import com.about.java.service.exceptions.NoSuchObjectException;
import com.about.java.service.exceptions.ObjectAlreadyExistsException;
import com.about.java.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaveServiceImpl implements SaveService{

    @Autowired
    private CareService careService;

    @Autowired
    private TreeService treeService;

    @Autowired
    private PoisonService poisonService;

    @Autowired
    private PestService pestService;

    @Transactional
    public void addStaticCare() {
        String[] arrayCare = new String[2];
        arrayCare[0] = "h";
        arrayCare[1] = "ell";

        for (String anArrayCare : arrayCare) {
            CareDTO careDTO = new CareDTO();
            careDTO.setDescribe(anArrayCare);
            try {
                careService.add(careDTO);
            } catch (ObjectAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    public void addStaticTree() {
        TreeDTO treeDTO1 = new TreeDTO();
        treeDTO1.setName("Береза");
        treeDTO1.setHeight("30-45м");
        treeDTO1.setDescribe("111");

        TreeDTO treeDTO2 = new TreeDTO();
        treeDTO2.setName("Ясень");
        treeDTO2.setHeight("25-35м");
        treeDTO2.setDescribe("222");

        try {
            List<CareDTO> careDTOs = careService.getCare();

            CareDTO careDTO1 = careDTOs.get(0);
            treeDTO1.setCareDTO(careDTO1);

            CareDTO careDTO2 = careDTOs.get(1);
            treeDTO2.setCareDTO(careDTO2);

        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        // Сохраняем объект Tree и возвращаем полученный от Hibernate id
        // Добавляем этот id в объект TreeDTO
        try {
            treeService.add(treeDTO1);
            treeService.add(treeDTO2);
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void addStaticPoison() {
        PoisonDTO poisonDTO1 = new PoisonDTO();
        poisonDTO1.setName("Эндобациллин");

        PoisonDTO poisonDTO2 = new PoisonDTO();
        poisonDTO2.setName("Бионефрин");

        // Сохраняем объекты PoisonDTO и получаем их id
        try {
            Long idPoison1 = poisonService.add(poisonDTO1);
            Long idPoison2 = poisonService.add(poisonDTO2);
            poisonDTO1.setId(idPoison1);
            poisonDTO2.setId(idPoison2);
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        updateStaticTree();
    }

    @Transactional
    public void addStaticPest() throws ObjectAlreadyExistsException{
        String[] arrayPests = new String[4];
        arrayPests[0] = "Нимфалида";
        arrayPests[1] = "Листовертка";
        arrayPests[2] = "Медведка";
        arrayPests[3] = "Майский жук";

        for (String arrayPest : arrayPests) {
            PestDTO pestDTO = new PestDTO();
            pestDTO.setName(arrayPest);
            pestService.add(pestDTO);
        }

        updateStaticPoison();
    }
    @Transactional
    public void updateStaticTree() {
        List<TreeDTO> treeDTOs = new ArrayList<TreeDTO>();
        try {
            treeDTOs = treeService.get();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        try {
            List<PoisonDTO> poisonDTOs = poisonService.get();
            List<PoisonDTO> poisonDTOs1 = new ArrayList<PoisonDTO>();
            List<PoisonDTO> poisonDTOs2 = new ArrayList<PoisonDTO>();

            // Обновляем обратную связь между объектами PoisonDTO и TreeDTO
            PoisonDTO poisonDTO1 = poisonDTOs.get(0);
            List<TreeDTO> treeDTOs1 = new ArrayList<TreeDTO>();
            treeDTOs1.add(treeDTOs.get(0));
            poisonDTO1.setTreeDTOs(treeDTOs1);
            poisonService.update(poisonDTO1);

            PoisonDTO poisonDTO2 = poisonDTOs.get(1);
            List<TreeDTO> treeDTOs2 = new ArrayList<TreeDTO>();
            treeDTOs2.add(treeDTOs.get(1));
            poisonDTO2.setTreeDTOs(treeDTOs2);
            poisonService.update(poisonDTO2);

            poisonDTOs1.add(poisonDTO1);
            poisonDTOs2.add(poisonDTO2);

            treeDTOs.get(0).setPoisonDTOs(poisonDTOs1);
            treeDTOs.get(1).setPoisonDTOs(poisonDTOs2);

        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        try {
            treeService.update(treeDTOs.get(0));
            treeService.update(treeDTOs.get(1));
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateStaticPoison() {
        List<PoisonDTO> poisonDTOs = new ArrayList<PoisonDTO>();
        try {
            poisonDTOs = poisonService.get();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        List<PestDTO> pestDTOs = new ArrayList<PestDTO>();
        try {
            pestDTOs = pestService.get();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        // Обновляем обратную связь между объектами PestDTO и PoisonDTO
        for (int i = 0; i < pestDTOs.size(); i++) {

            PestDTO pestDTO = pestDTOs.get(i);

            if (i < 2){
                poisonDTOs.add(poisonDTOs.get(0));
            }
            else {
                poisonDTOs.add(poisonDTOs.get(1));
            }

            pestDTO.setPoisonDTOs(poisonDTOs);

            try {
                pestService.update(pestDTO);
            } catch (NoSuchObjectException e) {
                e.printStackTrace();
            }
        }

        List<PestDTO> pestDTOs1 = new ArrayList<PestDTO>();
        pestDTOs1.add(pestDTOs.get(0));
        pestDTOs1.add(pestDTOs.get(1));

        List<PestDTO> pestDTOs2 = new ArrayList<PestDTO>();
        pestDTOs2.add(pestDTOs.get(2));
        pestDTOs2.add(pestDTOs.get(3));

        poisonDTOs.get(0).setPestDTOs(pestDTOs1);
        poisonDTOs.get(1).setPestDTOs(pestDTOs2);

        try {
            poisonService.update(poisonDTOs.get(0));
            poisonService.update(poisonDTOs.get(1));
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
}

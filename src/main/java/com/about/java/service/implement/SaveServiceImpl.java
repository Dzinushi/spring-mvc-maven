package com.about.java.service.implement;

import com.about.java.dto.*;
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
    private TreeService treeService;

    @Autowired
    private PoisonService poisonService;

    @Autowired
    private PestService pestService;

    @Transactional
    public void addStaticTree() throws ObjectAlreadyExistsException {
        TreePoisonDTO treePoisonDTO1 = new TreePoisonDTO();
        TreeDTO treeDTO1 = new TreeDTO();
        treeDTO1.setName("Береза");
        treeDTO1.setHeight("30-45м");
        treeDTO1.setDescribe("111");

        TreePoisonDTO treePoisonDTO2 = new TreePoisonDTO();
        TreeDTO treeDTO2 = new TreeDTO();
        treeDTO2.setName("Ясень");
        treeDTO2.setHeight("25-35м");
        treeDTO2.setDescribe("222");

        CareDTO careDTO1 = new CareDTO();
        careDTO1.setDescribe("kill");
        treeDTO1.setCareDTO(careDTO1);

        CareDTO careDTO2 = new CareDTO();
        careDTO2.setDescribe("bill");
        treeDTO2.setCareDTO(careDTO2);

        treePoisonDTO1.setTreeDTO(treeDTO1);
        treePoisonDTO2.setTreeDTO(treeDTO2);

        // Сохраняем объект Tree и возвращаем полученный от Hibernate id
        // Добавляем этот id в объект TreeDTO
        try {
            List<TreePoisonDTO> treePoisonDTOs1 = new ArrayList<TreePoisonDTO>();
            treePoisonDTOs1.add(treePoisonDTO1);
            treeService.add(treePoisonDTOs1);

            List<TreePoisonDTO> treePoisonDTOs2 = new ArrayList<TreePoisonDTO>();
            treePoisonDTOs2.add(treePoisonDTO2);
            treeService.add(treePoisonDTOs2);
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void addStaticPoison() {
        List<TreePoisonDTO> treePoisonDTOs1 = new ArrayList<TreePoisonDTO>();
        List<TreePoisonDTO> treePoisonDTOs2 = new ArrayList<TreePoisonDTO>();

        PoisonDTO poisonDTO1 = new PoisonDTO();
        poisonDTO1.setName("Эндобациллин");
        TreePoisonDTO treePoisonDTO1 = new TreePoisonDTO();
        treePoisonDTO1.setPoisonDTO(poisonDTO1);
        treePoisonDTOs1.add(treePoisonDTO1);

        PoisonDTO poisonDTO2 = new PoisonDTO();
        poisonDTO2.setName("Бионефрин");
        TreePoisonDTO treePoisonDTO2 = new TreePoisonDTO();
        treePoisonDTO2.setPoisonDTO(poisonDTO2);
        treePoisonDTOs2.add(treePoisonDTO2);

        try {
            poisonService.add(treePoisonDTOs1, new ArrayList<PoisonPestDTO>());
            poisonService.add(treePoisonDTOs2, new ArrayList<PoisonPestDTO>());
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        // Добавление в TreeDTO зависимости на модель PoisonDTO
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

            List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();

            PestDTO pestDTO = new PestDTO();
            pestDTO.setName(arrayPest);

            PoisonPestDTO poisonPestDTO = new PoisonPestDTO();
            poisonPestDTO.setPestDTO(pestDTO);
            poisonPestDTOs.add(poisonPestDTO);

            pestService.add(poisonPestDTOs);
        }

        updateStaticPoison();
    }

    @Transactional
    public void updateStaticTree() {
        List<TreePoisonDTO> treePoisons = new ArrayList<TreePoisonDTO>();
        List<TreePoisonDTO> poisonTrees = new ArrayList<TreePoisonDTO>();
        try {
            treePoisons = treeService.get();
            poisonTrees = poisonService.getTreePoisons();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        // Обновляем связи объекта PoisonDTO
        TreePoisonDTO poisonTreeDTO1 = poisonTrees.get(0);
        poisonTreeDTO1.setTreeDTO(treePoisons.get(0).getTreeDTO());
        poisonTreeDTO1.setPoisonDTO(poisonTrees.get(0).getPoisonDTO());
        List<TreePoisonDTO> treePoisonDTOs1 = new ArrayList<TreePoisonDTO>();
        treePoisonDTOs1.add(poisonTreeDTO1);

        TreePoisonDTO poisonTreeDTO2 = poisonTrees.get(1);
        poisonTreeDTO2.setTreeDTO(treePoisons.get(1).getTreeDTO());
        poisonTreeDTO2.setPoisonDTO(poisonTrees.get(1).getPoisonDTO());
        List<TreePoisonDTO> treePoisonDTOs2 = new ArrayList<TreePoisonDTO>();
        treePoisonDTOs2.add(poisonTreeDTO2);

        // Обновляем связи объекта TreeDTO
        TreePoisonDTO treePoisonDTO1 = treePoisons.get(0);
        treePoisonDTO1.setPoisonDTO(poisonTreeDTO1.getPoisonDTO());
        treePoisons.set(0, treePoisonDTO1);

        TreePoisonDTO treePoisonDTO2 = treePoisons.get(1);
        treePoisonDTO2.setPoisonDTO(poisonTreeDTO2.getPoisonDTO());
        treePoisons.set(1, treePoisonDTO2);

        try {
            List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();
            poisonService.update(treePoisonDTOs1, poisonPestDTOs);
            poisonService.update(treePoisonDTOs2, poisonPestDTOs);

        } catch (NoSuchObjectException e) {
                e.printStackTrace();
        }

        try {
            List<TreePoisonDTO> treePoisons1 = new ArrayList<TreePoisonDTO>();
            treePoisons1.add(treePoisons.get(0));
            treeService.update(treePoisons1);

            List<TreePoisonDTO> treePoisons2 = new ArrayList<TreePoisonDTO>();
            treePoisons2.add(treePoisons.get(1));
            treeService.update(treePoisons2);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateStaticPoison() {
        List<TreePoisonDTO> treePoisonDTOs = new ArrayList<TreePoisonDTO>();
        try {
            treePoisonDTOs = poisonService.getTreePoisons();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        List<PoisonPestDTO> pestPoisons = new ArrayList<PoisonPestDTO>();
        try {
            pestPoisons = pestService.get();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }

        // Обновляем связи объекта PestDTO
        for (int i = 0; i < pestPoisons.size(); i++) {

            List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();
            PoisonPestDTO poisonPestDTO = new PoisonPestDTO();
            poisonPestDTO.setPestDTO(pestPoisons.get(i).getPestDTO());

            if (i < 2){
                poisonPestDTO.setPoisonDTO(treePoisonDTOs.get(0).getPoisonDTO());
            }
            else {
                poisonPestDTO.setPoisonDTO(treePoisonDTOs.get(1).getPoisonDTO());
            }

            poisonPestDTOs.add(poisonPestDTO);

            try {
                pestService.update(poisonPestDTOs);
            } catch (NoSuchObjectException e) {
                e.printStackTrace();
            }
        }

        // Обновляем связи объекта PoisonDTO
        for (int i = 0; i <  2; ++i) {
            List<PoisonPestDTO> poisonPestDTOs = new ArrayList<PoisonPestDTO>();
            for (int j = i * 2; j < (i * 2) + 2; ++j) {
                PoisonPestDTO poisonPestDTO = new PoisonPestDTO();
                poisonPestDTO.setPoisonDTO(treePoisonDTOs.get(i).getPoisonDTO());
                poisonPestDTO.setPestDTO(pestPoisons.get(j).getPestDTO());
                poisonPestDTOs.add(poisonPestDTO);
            }
            List<TreePoisonDTO> treePoisonDTOs1 = new ArrayList<TreePoisonDTO>();
            treePoisonDTOs1.add(treePoisonDTOs.get(i));
            try {
                poisonService.update(treePoisonDTOs1, poisonPestDTOs);
            } catch (NoSuchObjectException e) {
                e.printStackTrace();
            }
        }
    }
}

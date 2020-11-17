package com.csc394.capStoneProject.dto;

import java.util.ArrayList;
import java.util.List;

import com.csc394.capStoneProject.entities.Teams;

import lombok.Data;

@Data

public class TeamsDTO {


    private Long teamId;


    private Long userId;


    private String teamName;

    private Integer numberOfMembers;

    private Integer numberOfGoals;

//


    public static TeamsDTO entityToDTO(Teams teamsEntity){

        TeamsDTO teamsDTO = new TeamsDTO();
        if(teamsEntity!=null) {
            teamsDTO.setTeamId(teamsEntity.getId());
            teamsDTO.setUserId(teamsEntity.getUserId());
            teamsDTO.setTeamName(teamsEntity.getTeamName());
            teamsDTO.setNumberOfMembers(teamsEntity.getUser().size());
            teamsDTO.setNumberOfGoals(teamsEntity.getGoals().size());


        }
        return teamsDTO;
    }
    
    public static Teams DTOToEntity(TeamsDTO teamsDTO){

        Teams teams = new Teams();
        if(teamsDTO!=null) {
        	teams.setId(teamsDTO.getTeamId());
        	teams.setUserId(teamsDTO.getUserId());
        	teams.setTeamName(teamsDTO.getTeamName());
        	


        }
        return teams;
    }
    public static List<TeamsDTO> listEntityToDTO(List<Teams> teamsEntity){
        List<TeamsDTO> teamsDTO = new ArrayList<>();
         teamsEntity.forEach(i-> teamsDTO.add(entityToDTO(i)));

         return teamsDTO;
    }





}

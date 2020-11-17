package com.csc394.capStoneProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csc394.capStoneProject.dto.TeamsDTO;
import com.csc394.capStoneProject.entities.Teams;
import com.csc394.capStoneProject.services.TeamsService;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    @Autowired
    TeamsService teamsService;

    @DeleteMapping("/{id}")
    public void deleteTeams(@PathVariable(name = "id") Long teamId) {
        teamsService.deleteTeam(teamId);  
    }



     @GetMapping("/{id}")
     public ResponseEntity<TeamsDTO> getTeams(@PathVariable(name = "id") Long teamId) {

         Teams teams = teamsService.findById(teamId);
         return ResponseEntity.ok().body(TeamsDTO.entityToDTO(teams));
     }
//
     @GetMapping("/professors/{id}")
    public ResponseEntity<List<TeamsDTO>> getTeamsByProffesor(@PathVariable(name ="id") Long professorId){

         return ResponseEntity.ok()
                 .body(TeamsDTO.listEntityToDTO(teamsService.findAllTeamByProfessorId(professorId)));
     }



    @PostMapping()
    public ResponseEntity<TeamsDTO> addTeam(@RequestBody TeamsDTO team) {
        return ResponseEntity.ok().body(TeamsDTO.entityToDTO(teamsService.addTeam(TeamsDTO.DTOToEntity(team))));
    }



}





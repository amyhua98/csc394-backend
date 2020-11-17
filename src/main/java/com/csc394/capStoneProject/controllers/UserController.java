package com.csc394.capStoneProject.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc394.capStoneProject.dto.AssignStdToTeamDTO;
import com.csc394.capStoneProject.dto.UserDTO;
import com.csc394.capStoneProject.repositories.TeamsRepository;
import com.csc394.capStoneProject.repositories.UserRepository;
import com.csc394.capStoneProject.services.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TeamsRepository teamsRepository;
    @Autowired
    UserRepository userRepository;



   @GetMapping("/teams/{id}")
   public ResponseEntity<List<UserDTO>> listStudentsInTeam(@PathVariable(name = "id") Long teamId){

       return  ResponseEntity.ok().body(UserDTO.listEntityToDTO(userService.getUserStudentsByTeamId(teamId)));
   }

    @RequestMapping(value="/addUser", method = RequestMethod.POST )
    public ResponseEntity<UserDTO> addUser(
            @RequestBody UserDTO userdto) {
        return ResponseEntity.ok().body(UserDTO.entityToDTO(userService.addUser(UserDTO.dtoToEntity(userdto))));
    }

    @PostMapping("/faculty/addUserToTeam")
    public ResponseEntity<UserDTO> addUserToTeam(
           @RequestBody AssignStdToTeamDTO assignStdToTeamDTO) {
        userService.addUserToTeam(assignStdToTeamDTO);
        return ResponseEntity.ok().body(new UserDTO());
    }
    
    @PostMapping("/faculty/deleteUserTeam")
    public ResponseEntity<UserDTO> deleteUserForTeam(
           @RequestBody AssignStdToTeamDTO assignStdToTeamDTO) {
        userService.deleteUerFromTeam(assignStdToTeamDTO);
        return ResponseEntity.ok().body(new UserDTO());
    }

    @RequestMapping(value = "/allStudentUsers", method = RequestMethod.GET)
    public ResponseEntity <List<UserDTO>> getAllStudents(){
       return ResponseEntity.ok().body(UserDTO.listEntityToDTO(userService.getStudentUsers()));
    }

}






package com.csc394.capStoneProject.controllers;

import java.util.ArrayList;
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

import com.csc394.capStoneProject.dto.AssignGoalToTeam;
import com.csc394.capStoneProject.dto.GoalsDTO;
import com.csc394.capStoneProject.entities.Goals;
import com.csc394.capStoneProject.entities.Status;
import com.csc394.capStoneProject.repositories.GoalsRepository;
import com.csc394.capStoneProject.services.GoalsServices;

@Controller
@RequestMapping("/api/goals")
public class GoalsController {

@Autowired
GoalsServices goalsServices;
@Autowired
    GoalsRepository goalsRepository;



    @GetMapping("/allGoals")
    public ResponseEntity<List<Goals>> getAllGoals(){

        return ResponseEntity.ok().body(goalsServices.getAllGoals());
    }

    @GetMapping("/numberOfAllGoals")

    public ResponseEntity<Integer> getNumberOfGoals(){

         return ResponseEntity.ok().body(goalsServices.getAllGoals().size());
    }

    @GetMapping("/numberOfGoalsDone")

    public ResponseEntity<Integer> getGallsDone(){
        List<Goals> goalsDone = new ArrayList<>();
       goalsServices.getAllGoals().forEach(goal->{
//           if(goal.getStatus() == Status.DONE){
//               goalsDone.add(goal);
//           }
       });
       return  ResponseEntity.ok().body(goalsDone.size());
    }




    @GetMapping("/teamGoal/{id}")
    public ResponseEntity <List<GoalsDTO>> getGoalByTeamId(@PathVariable(name ="id") Long teamId ){

        return ResponseEntity.ok()
                .body(GoalsDTO.listEntityToDTO(goalsServices.findGoalByTeamId(teamId)));
    }



    @RequestMapping(value="/grade", method = RequestMethod.PUT)

    public ResponseEntity<GoalsDTO> setGrade(@RequestBody Goals goals) {
        return ResponseEntity.ok().body(GoalsDTO.entityToDTO(goalsServices.grading(goals.getId(), goals.getGrades())));
    }


    @PostMapping("/faculty/addGoalsToTeam")
    public ResponseEntity<GoalsDTO> addGoalsToTeam(
            @RequestBody GoalsDTO goalDTO) {

        Goals goals = goalsServices.addGoalsToTeam(goalDTO);
        goalDTO.setId(goals.getId());
        return ResponseEntity.ok().body(goalDTO);
    }

    @RequestMapping(value="/updateStatus", method = RequestMethod.PUT)
    public ResponseEntity<GoalsDTO> updateGoalStatus(@RequestBody GoalsDTO goalsDTO){
       // return ResponseEntity.ok().body(GoalsDTO.entityToDTO(goalsServices.changeStatus(goalsDTO.getId(),goalsDTO.getStatus())));
    return null;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<GoalsDTO> deleteById(
            @PathVariable Long id) {

        goalsServices.deleteGoalById(id);
        return ResponseEntity.ok().body(new GoalsDTO());
    }
}

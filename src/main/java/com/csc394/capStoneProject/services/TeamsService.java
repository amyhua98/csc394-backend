package com.csc394.capStoneProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc394.capStoneProject.entities.Goals;
import com.csc394.capStoneProject.entities.Reviews;
import com.csc394.capStoneProject.entities.Teams;
import com.csc394.capStoneProject.entities.User;
import com.csc394.capStoneProject.repositories.GoalsRepository;
import com.csc394.capStoneProject.repositories.ReviewsRepository;
import com.csc394.capStoneProject.repositories.TeamsRepository;
import com.csc394.capStoneProject.repositories.UserRepository;

@Service
public class TeamsService {

    @Autowired
    TeamsRepository teamsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewsRepository reviewRepository;
    
    @Autowired
    GoalsRepository goalsRepository;
    //   get all team by id.

    public Teams findById(Long teamById) {
        return teamsRepository.findById(teamById).get();
    }


    public List<Teams> findAllTeamByProfessorId(Long professorId) {


        return teamsRepository.findByProfessor(userRepository.getOne(professorId));

    }

    public List<Teams> getAllTeams() {
    	return teamsRepository.findAll();
	}
    
    public void deleteTeam(Long idTeam) {
    	Optional<Teams> team = teamsRepository.findById(idTeam);
    	if (team.isPresent()) {
    		
    		for (int i=0; i<goalsRepository.findByTeams(team.get()).size();i++){
    			Goals goal = goalsRepository.findById(goalsRepository.findByTeams(team.get()).get(i).getId()).get() ;
    			goal.setTeams(null);
    			goalsRepository.save(goal);
    		}
    		
    		for (int i=0; i<reviewRepository.findByTeams(team.get()).size();i++){
    			Reviews reviews = reviewRepository.findById(reviewRepository.findByTeams(team.get()).get(i).getId()).get() ;
    			reviews.setTeams(null);
    			reviewRepository.save(reviews);
    		}
    		
    		for (int i=0; i<userRepository.findByTeamId(team.get().getId()).size();i++){
    			User user = userRepository.findById(userRepository.findByTeamId(team.get().getId()).get(i).getId()).get() ;
    			user.setTeamId(null);
        		userRepository.save(user);
    		}
    		teamsRepository.save(team.get());
    		teamsRepository.delete(team.get());
    	}
    }


    public Teams addTeam(Teams team) {


        return  teamsRepository.save(team);
    }




}







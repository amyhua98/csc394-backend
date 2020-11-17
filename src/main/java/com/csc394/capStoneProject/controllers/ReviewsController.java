package com.csc394.capStoneProject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csc394.capStoneProject.dto.ReviewsDTO;
import com.csc394.capStoneProject.entities.Reviews;
import com.csc394.capStoneProject.entities.User;
import com.csc394.capStoneProject.repositories.UserRepository;
import com.csc394.capStoneProject.services.ReviewsServices;

@Controller
@RequestMapping("/api/reviews")
public class ReviewsController {


@Autowired
    ReviewsServices reviewsServices;

@Autowired
UserRepository userRepository;


	@GetMapping(value = "/reviewsByTeamId/{id}")
    public ResponseEntity<List<ReviewsDTO>> getReviewsByTeamId(@PathVariable(value = "id") Long teamId){

		return ResponseEntity.ok().body(ReviewsDTO.listEntityToDTO(reviewsServices.findReviewsByTeamId(teamId)));
	}

	@PostMapping()
    public ResponseEntity<ReviewsDTO> saveReview(@RequestBody ReviewsDTO reviewDTO){
		Reviews review =  reviewsServices.saveReview(ReviewsDTO.DTOToEntity(reviewDTO));
		
		reviewDTO.setId(review.getId());
		Optional<User> user = userRepository.findById(reviewDTO.getUserId());
		reviewDTO.setRaterFullName(user.get().getFirstName() + " " + user.get().getLastName());
    return ResponseEntity.ok().body(reviewDTO);
}

}

package com.csc394.capStoneProject.dto;

import com.csc394.capStoneProject.entities.Goals;
import com.csc394.capStoneProject.entities.Reviews;
import com.csc394.capStoneProject.entities.Teams;
import com.csc394.capStoneProject.entities.User;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data

public class ReviewsDTO {


    private Long id;
    private Long userId;
    private Long teamId;
    private Integer rating;
    private String comment;
    private String raterFullName;


    public static ReviewsDTO entityToDTO(Reviews reviewsEntity){

        ReviewsDTO reviewsDTO = new ReviewsDTO();

        reviewsDTO.setId(reviewsEntity.getId());
        reviewsDTO.setRating(reviewsEntity.getRating());
        reviewsDTO.setTeamId(reviewsEntity.getTeams().getId());
        reviewsDTO.setComment(reviewsEntity.getComment());
        reviewsDTO.setUserId(reviewsEntity.getUser().getId());
        reviewsDTO.setRaterFullName(reviewsEntity.getUser().getFirstName() + " " + reviewsEntity.getUser().getLastName());
        return reviewsDTO;
    }
    
    
    public static Reviews DTOToEntity(ReviewsDTO reviewsDTO){

    	Reviews reviews = new Reviews();

    	reviews.setId(reviewsDTO.getId());
    	reviews.setRating(reviewsDTO.getRating());
    	Teams teams = new Teams();
    	teams.setId(reviewsDTO.getTeamId());
    	reviews.setTeams(teams);
    	reviews.setComment(reviewsDTO.getComment());
    	User user = new User();
    	user.setId(reviewsDTO.getUserId());
    	reviews.setUser(user);
       
        return reviews;
    }

    public static List<ReviewsDTO> listEntityToDTO(List<Reviews> reviewsEntity) {

        List<ReviewsDTO> reviewsDTO = new ArrayList<>();
        reviewsEntity.forEach(i -> {
            reviewsDTO.add(entityToDTO(i));


        });

        return reviewsDTO;
    }


}

package com.csc394.capStoneProject.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc394.capStoneProject.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
    public List<User> findByTeamIdAndRoleId(Long teamId, Long id);
    
    public List<User> findByTeamId(Long teamId);

}

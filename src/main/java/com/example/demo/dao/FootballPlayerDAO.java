package com.example.demo.dao;

import com.example.demo.entities.FootballPlayers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballPlayerDAO extends JpaRepository<FootballPlayers, Long> {

}

package com.example.demo.entities;

import lombok.*;
import org.springframework.context.annotation.Bean;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FootballPlayersDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private int age;
    private String footballClub;

    public FootballPlayersDTO(FootballPlayers footballPlayers) {
        this.id=footballPlayers.getId();
        this.firstName = footballPlayers.getFirstName();
        this.lastName = footballPlayers.getLastName();
        this.nationality = footballPlayers.getNationality();
        this.age = footballPlayers.getAge();
        this.footballClub = footballPlayers.getFootballClub();
    }
}

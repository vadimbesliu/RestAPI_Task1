package com.example.demo.entities;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@Table(name="players")
@NoArgsConstructor
public class FootballPlayers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String nationality;
    @NotNull
    private int age;
    private String footballClub;

    public FootballPlayers(String firstName, String lastName, String nationality, int age, String footballClub) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.age = age;
        this.footballClub = footballClub;
    }

    public FootballPlayers(FootballPlayersDTO footballPlayersDTO) {
        this.id=footballPlayersDTO.getId();
        this.firstName = footballPlayersDTO.getFirstName();
        this.lastName = footballPlayersDTO.getLastName();
        this.nationality = footballPlayersDTO.getNationality();
        this.age = footballPlayersDTO.getAge();
        this.footballClub = footballPlayersDTO.getFootballClub();
    }
}

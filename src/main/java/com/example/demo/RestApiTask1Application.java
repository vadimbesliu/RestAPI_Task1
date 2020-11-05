package com.example.demo;

import com.example.demo.dao.FootballPlayerDAO;
import com.example.demo.entities.FootballPlayers;
import com.example.demo.entities.FootballPlayersDTO;
import com.example.demo.services.FootballPlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiTask1Application {

	public static void main(String[] args) {
		SpringApplication.run(RestApiTask1Application.class, args);
	}

	@Bean
	public CommandLineRunner addPlayers(FootballPlayerDAO footballPlayerDAO) {
		return (args) -> {
			footballPlayerDAO.save(new FootballPlayers("Karim","Benzema","France",32,"Real Madrid"));
			footballPlayerDAO.save(new FootballPlayers("Lionel","Messi","Argentine",32,"Barcelona"));
			footballPlayerDAO.save(new FootballPlayers("Luka","Modric","Croatia",34,"Real Madrid"));
			footballPlayerDAO.save(new FootballPlayers("Antoine","Griezman","France",29,"Barcelona"));
			footballPlayerDAO.save(new FootballPlayers("Mohamed","Salah","Egypt",28,"Liverpool"));
			footballPlayerDAO.save(new FootballPlayers("Neymar","Jr","Brazil",28,"PSG"));
			footballPlayerDAO.save(new FootballPlayers("Gerard","Pique","Spain",32,"Barcelona"));
			footballPlayerDAO.save(new FootballPlayers("Lautaro","Martinez","Argentine",24,"Inter"));
			footballPlayerDAO.save(new FootballPlayers("Cristiano","Ronaldo","Portugal",34,"Juventus"));
			footballPlayerDAO.save(new FootballPlayers("Marcus","Rashford","England",22,"Manchester United"));
			footballPlayerDAO.save(new FootballPlayers("Lucas","Moura","Brazil",28,"Tottenham"));
			footballPlayerDAO.save(new FootballPlayers("James","Rodriguez","Columbia",28,"Everton"));
		};
	}

}

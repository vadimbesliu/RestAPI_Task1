package com.example.demo.services;

import com.example.demo.dao.FootballPlayerDAO;
import com.example.demo.entities.FootballPlayers;
import com.example.demo.entities.FootballPlayersDTO;
import com.example.demo.exceptionHandler.CustomFootballPlayersServiceException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class FootballPlayerService {
    private final FootballPlayerDAO footballPlayerDAO;

    public List<FootballPlayersDTO> getFootballPlayers() throws CustomFootballPlayersServiceException {
        try {
            List<FootballPlayersDTO> footballPlayersDTO = new ArrayList<>();
            for (FootballPlayers footballPlayers : footballPlayerDAO.findAll()) {
                footballPlayersDTO.add(new FootballPlayersDTO(footballPlayers));
            }
            return footballPlayersDTO;
        } catch (Exception e) {
            throw new CustomFootballPlayersServiceException("Could not get the football Players", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void addFootballPlayers(FootballPlayersDTO footballPlayersDTO) throws CustomFootballPlayersServiceException {
        validateFootballPlayer(footballPlayersDTO);
        try {
            footballPlayerDAO.save(new FootballPlayers(footballPlayersDTO));
        } catch (Exception e) {
            throw new CustomFootballPlayersServiceException("Football player could not be saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void removeFootballPlayer(Long id) throws CustomFootballPlayersServiceException {
        try {
            footballPlayerDAO.deleteById(id);
        } catch (Exception e) {
            throw new CustomFootballPlayersServiceException("Could not delete football player", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public FootballPlayersDTO getOneFootballPlayer(Long id) throws CustomFootballPlayersServiceException {
        Optional<FootballPlayers> optionalFootballPlayer = footballPlayerDAO.findById(id);
        if (optionalFootballPlayer.isPresent()) {
            return new FootballPlayersDTO(optionalFootballPlayer.get());
        }
        throw new CustomFootballPlayersServiceException("The football player could not be found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public void updateFootballPlayer(FootballPlayersDTO footballPlayersDTO) throws CustomFootballPlayersServiceException {
        Optional<FootballPlayers> optionalFootballPlayers = footballPlayerDAO.findById(footballPlayersDTO.getId());
        footballPlayerPresent(optionalFootballPlayers);
        validateFootballPlayer(footballPlayersDTO);
        FootballPlayers toBeUpdated = optionalFootballPlayers.get();
        toBeUpdated.setFirstName(footballPlayersDTO.getFirstName());
        toBeUpdated.setLastName(footballPlayersDTO.getLastName());
        toBeUpdated.setAge(footballPlayersDTO.getAge());
        toBeUpdated.setFootballClub(footballPlayersDTO.getFootballClub());
        toBeUpdated.setNationality(footballPlayersDTO.getNationality());
        try {
            footballPlayerDAO.save(toBeUpdated);
        } catch (Exception e) {
            throw new CustomFootballPlayersServiceException("Data Source issue, football player could not be updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateFootballPlayer(FootballPlayersDTO footballPlayersDTO) throws CustomFootballPlayersServiceException {
        try {
            if (validateName(footballPlayersDTO.getFirstName())) {
                if (validateName(footballPlayersDTO.getLastName())) {
                    validateAge(footballPlayersDTO.getAge());
                    return true;
                } else {
                    throw new CustomFootballPlayersServiceException("Invalid last Name", HttpStatus.BAD_REQUEST);
                }
            }
            throw new CustomFootballPlayersServiceException("Invalid name", HttpStatus.BAD_REQUEST);
        } catch (NullPointerException npe) {
            throw new CustomFootballPlayersServiceException("No null values", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateAge(Integer age) throws CustomFootballPlayersServiceException {
        if (age >= 0 && age < 60) {
            return;
        }
        throw new CustomFootballPlayersServiceException("Invalid age", HttpStatus.BAD_REQUEST);
    }

    private void footballPlayerPresent(Optional<FootballPlayers> optionalFootballPlayers) throws CustomFootballPlayersServiceException {
        if (optionalFootballPlayers.isPresent()) {
            return;
        }
        throw new CustomFootballPlayersServiceException("Football player not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateName(String name) {
        return !name.isEmpty() && !name.contains(" ") && name.length() >= 1 && !name.matches(".*\\d.*");
    }
}

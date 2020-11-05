package com.example.demo.restControlers;

import com.example.demo.entities.FootballPlayers;
import com.example.demo.entities.FootballPlayersDTO;
import com.example.demo.exceptionHandler.ExceptionsHandler;
import com.example.demo.services.FootballPlayerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/footballPlayers")
public class RestFootballPlayerController {
    private final FootballPlayerService footballPlayerService;
    private final ExceptionsHandler exceptionsHandler;

    @GetMapping("/")
    public ResponseEntity<Object> footballPlayers() throws Exception {
        return new ResponseEntity<>(footballPlayerService.getFootballPlayers(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody FootballPlayersDTO footballPlayersDTO) throws Exception {
        footballPlayerService.addFootballPlayers(footballPlayersDTO);
        return new ResponseEntity<>("All good, football Player saved", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id) throws Exception {
        footballPlayerService.removeFootballPlayer(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserDetails(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(footballPlayerService.getOneFootballPlayer(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUserDetails(@PathVariable Long id, @RequestBody FootballPlayersDTO footballPlayersDTO) throws Exception {
        footballPlayersDTO.setId(id);
        footballPlayerService.updateFootballPlayer(footballPlayersDTO);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }
}

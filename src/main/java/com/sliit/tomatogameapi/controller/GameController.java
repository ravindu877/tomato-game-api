package com.sliit.tomatogameapi.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sliit.tomatogameapi.dto.UserWiseGameStatusDto;
import com.sliit.tomatogameapi.service.GameService;
import com.sliit.tomatogameapi.service.impl.UtilityServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/game-service")
@Tags()
public class GameController {

    private final GameService gameService;

    private final UtilityServiceImpl utilityService;

    @GetMapping("/get-game-status/{username}")
    public ResponseEntity<UserWiseGameStatusDto> getGameStatus(@RequestHeader("Authorization") String token, @PathVariable String username) {
        try {
            if (utilityService.requestAuthentication(token)) {
                return new ResponseEntity<UserWiseGameStatusDto>(gameService.getGameStatus(username), HttpStatus.OK);
            }
        } catch (TokenExpiredException e) {
            throw new TokenExpiredException(e.getMessage());
        }
        return new ResponseEntity<UserWiseGameStatusDto>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/start-new-game/{gameId}/{difficultyLevel}")
    public ResponseEntity<UserWiseGameStatusDto> startNewGame(@RequestHeader("Authorization") String token, @PathVariable Long gameId, @PathVariable Integer difficultyLevel) {
        try {
            if (utilityService.requestAuthentication(token)) {
                return new ResponseEntity<UserWiseGameStatusDto>(gameService.startNewGame(gameId,difficultyLevel), HttpStatus.OK);
            }
        } catch (TokenExpiredException e) {
            throw new TokenExpiredException(e.getMessage());
        }
        return new ResponseEntity<UserWiseGameStatusDto>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/update-game-status/{gameId}/{currentScore}/{difficultyLevel}")
    public ResponseEntity<UserWiseGameStatusDto> updateGameStatus(@RequestHeader("Authorization") String token, @PathVariable Long gameId, @PathVariable Integer currentScore, @PathVariable Integer difficultyLevel) {
        try {
            if (utilityService.requestAuthentication(token)) {
                return new ResponseEntity<UserWiseGameStatusDto>(gameService.updateGameStatus(gameId,currentScore,difficultyLevel), HttpStatus.OK);
            }
        } catch (TokenExpiredException e) {
            throw new TokenExpiredException(e.getMessage());
        }
        return new ResponseEntity<UserWiseGameStatusDto>(HttpStatus.UNAUTHORIZED);
    }

}

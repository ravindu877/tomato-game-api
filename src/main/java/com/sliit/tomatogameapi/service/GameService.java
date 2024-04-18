package com.sliit.tomatogameapi.service;

import com.sliit.tomatogameapi.dto.UserWiseGameStatusDto;

public interface GameService {
    Boolean initializeGame(Long userId);
    UserWiseGameStatusDto getGameStatus(String username);
    UserWiseGameStatusDto startNewGame(Long gameId, Integer difficultyLevel);
    UserWiseGameStatusDto updateGameStatus(Long gameId, Integer currentScore, Integer difficultyLevel);
}

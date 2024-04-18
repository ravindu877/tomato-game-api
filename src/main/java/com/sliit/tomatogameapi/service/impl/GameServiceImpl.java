package com.sliit.tomatogameapi.service.impl;

import com.sliit.tomatogameapi.dto.UserWiseGameStatusDto;
import com.sliit.tomatogameapi.entity.UserMst;
import com.sliit.tomatogameapi.entity.UserWiseGameStatus;
import com.sliit.tomatogameapi.repository.UserMstRepository;
import com.sliit.tomatogameapi.repository.UserWiseGameStatusRepository;
import com.sliit.tomatogameapi.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final UserWiseGameStatusRepository userWiseGameStatusRepository;
    private final UserMstRepository userMstRepository;
    private final ModelMapper modelMapper;


    @Override
    public Boolean initializeGame(Long userId) {
        UserMst userMst = UserMst.builder().userId(userId).build();
        UserWiseGameStatus build = UserWiseGameStatus.builder()
                .level(1)
                .highestScore(0)
                .attemptCount(0)
                .currentScore(0)
                .currentAttemptCount(0)
                .remainingLifeCount(3)
                .currentDifficultyLevel(0)
                .userMst(userMst)
                .build();
        userWiseGameStatusRepository.save(build);
        return true;
    }

    @Override
    public UserWiseGameStatusDto getGameStatus(String username) {
        try {

            if (Objects.nonNull(username)) {

                UserMst userMst = userMstRepository.findByUsername(username);
                if (Objects.nonNull(userMst)) {

                    UserWiseGameStatus userWiseGameStatus = userWiseGameStatusRepository.findByUserMst(userMst);
                    return userWiseGameStatus.toDto(modelMapper);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserWiseGameStatusDto startNewGame(Long gameId, Integer difficultyLevel) {
        try {
            if (Objects.nonNull(difficultyLevel) && Objects.nonNull(gameId)) {
                UserWiseGameStatus userWiseGameStatus = userWiseGameStatusRepository.getReferenceById(gameId);
                userWiseGameStatus.setRemainingLifeCount(3);
                userWiseGameStatus.setCurrentDifficultyLevel(difficultyLevel);
                userWiseGameStatus.setCurrentScore(0);
                userWiseGameStatus.setCurrentAttemptCount(0);
                UserWiseGameStatus save = userWiseGameStatusRepository.save(userWiseGameStatus);
                return save.toDto(modelMapper);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserWiseGameStatusDto();
    }

    @Override
    public UserWiseGameStatusDto updateGameStatus(Long gameId, Integer currentScore, Integer difficultyLevel) {
        try {

            if (Objects.nonNull(gameId) && Objects.nonNull(currentScore)) {

                UserWiseGameStatus userWiseGameStatus = userWiseGameStatusRepository.getReferenceById(gameId);

                updateCurrentGameStatus(userWiseGameStatus,currentScore,difficultyLevel);
                UserWiseGameStatus save = userWiseGameStatusRepository.save(userWiseGameStatus);

                return save.toDto(modelMapper);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserWiseGameStatusDto();
    }

    private void updateCurrentGameStatus(UserWiseGameStatus userWiseGameStatus, Integer currentScore, Integer difficultyLevel) {

        userWiseGameStatus.setCurrentScore(Integer.sum(userWiseGameStatus.getCurrentScore(),currentScore));
        userWiseGameStatus.setCurrentAttemptCount(Integer.sum(userWiseGameStatus.getCurrentAttemptCount(),1));
        userWiseGameStatus.setCurrentDifficultyLevel(difficultyLevel);

        if (currentScore.equals(0)) {
            userWiseGameStatus.setRemainingLifeCount(userWiseGameStatus.getRemainingLifeCount() - 1);
        }

        if (Integer.remainderUnsigned(userWiseGameStatus.getCurrentScore(),32) == 0 && !currentScore.equals(0)) {
            userWiseGameStatus.setLevel(Integer.sum(userWiseGameStatus.getLevel(),1));
        }

        if (userWiseGameStatus.getRemainingLifeCount().equals(0)) {

            if (userWiseGameStatus.getCurrentScore().compareTo(userWiseGameStatus.getHighestScore()) >= 0) {
                userWiseGameStatus.setHighestScore(userWiseGameStatus.getCurrentScore());
                userWiseGameStatus.setAttemptCount(userWiseGameStatus.getCurrentAttemptCount());
            }

        }

    }

}

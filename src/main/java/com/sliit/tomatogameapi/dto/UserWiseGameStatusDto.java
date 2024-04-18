package com.sliit.tomatogameapi.dto;

import com.sliit.tomatogameapi.entity.SuperEntity;
import com.sliit.tomatogameapi.entity.UserWiseGameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserWiseGameStatusDto extends SuperDto<UserWiseGameStatus> {
    private Long id;
    private Integer level;
    private Integer highestScore;
    private Integer attemptCount;
    private Integer currentScore;
    private Integer currentAttemptCount;
    private Integer currentDifficultyLevel;
    private Integer remainingLifeCount;
    private String username;

    @Override
    public UserWiseGameStatus toEntity(ModelMapper modelMapper) {
        return null;
    }
}

package com.sliit.tomatogameapi.entity;

import com.sliit.tomatogameapi.dto.UserWiseGameStatusDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class UserWiseGameStatus extends SuperEntity<UserWiseGameStatusDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level;
    private Integer highestScore;
    private Integer attemptCount;
    private Integer currentScore;
    private Integer currentAttemptCount;
    private Integer currentDifficultyLevel;
    private Integer remainingLifeCount;



    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserMst userMst;

    @Override
    public UserWiseGameStatusDto toDto(ModelMapper modelMapper) {
        UserWiseGameStatusDto userWiseGameStatusDto = modelMapper.map(this,UserWiseGameStatusDto.class);
        userWiseGameStatusDto.setUsername(this.userMst.getUsername());
        return userWiseGameStatusDto;
    }
}

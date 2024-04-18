package com.sliit.tomatogameapi.entity;

import com.sliit.tomatogameapi.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class UserMst extends SuperEntity<UserDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String userPassword;


    @OneToOne(mappedBy = "userMst")
    private UserWiseGameStatus userWiseGameStatus;


    @Override
    public UserDto toDto(ModelMapper modelMapper) {
        return modelMapper.map(this,UserDto.class);
    }
}

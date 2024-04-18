package com.sliit.tomatogameapi.dto;

import com.sliit.tomatogameapi.entity.UserMst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto extends SuperDto<UserMst> {
    private Long userId;
    private String username;
    private String password;


    @Override
    public UserMst toEntity(ModelMapper modelMapper) {
        return modelMapper.map(this,UserMst.class);
    }
}

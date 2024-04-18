package com.sliit.tomatogameapi.service;

import com.sliit.tomatogameapi.dto.UserDto;

public interface UserMstService {
    Boolean userSave(UserDto userDto);
    String userLogin(UserDto userDto);
    Boolean validateUser(String username);
}

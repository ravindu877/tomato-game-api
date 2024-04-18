package com.sliit.tomatogameapi.controller;

import com.sliit.tomatogameapi.dto.UserDto;
import com.sliit.tomatogameapi.service.UserMstService;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/user-service")
@Tags()
public class UserController {

    private final UserMstService userMstService;

    @PostMapping("/save-user")
    public ResponseEntity<Boolean> saveUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<Boolean>(userMstService.userSave(userDto), HttpStatus.OK);
    }

    @PostMapping("/user-login")
    public ResponseEntity<String> userLogin(@RequestBody UserDto userDto) {
        return new ResponseEntity<String>(userMstService.userLogin(userDto), HttpStatus.OK);
    }

}

package com.sliit.tomatogameapi.service.impl;

import com.sliit.tomatogameapi.dto.UserDto;
import com.sliit.tomatogameapi.entity.UserMst;
import com.sliit.tomatogameapi.exception.AlreadyExistException;
import com.sliit.tomatogameapi.exception.InternalServerException;
import com.sliit.tomatogameapi.exception.ValidateException;
import com.sliit.tomatogameapi.repository.UserMstRepository;
import com.sliit.tomatogameapi.service.GameService;
import com.sliit.tomatogameapi.service.UserMstService;
import com.sliit.tomatogameapi.util.MessageConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserMstServiceImpl implements UserMstService {

    private final UserMstRepository userMstRepository;

    private final GameService gameService;

    private final ModelMapper modelMapper;

    private final UtilityServiceImpl utilityService;


    @Transactional
    @Override
    public Boolean userSave(UserDto userDto) {
        try {

            if (Objects.nonNull(userDto)) {

                if (validateUser(userDto.getUsername()))
                    throw new AlreadyExistException(MessageConstant.USERNAME_ALREADY_EXISTING);

                UserMst userMst = userDto.toEntity(modelMapper);
                userMst.setUserPassword(utilityService.hidePassword(userDto.getPassword()));
                UserMst save = userMstRepository.save(userMst);
                gameService.initializeGame(save.getUserId());
                return true;

            }

        } catch (AlreadyExistException e) {
            throw new AlreadyExistException(e.getMessage());
        } catch (Exception e) {
            log.error("User Save | Error | " + e.getMessage());
            throw new InternalServerException(MessageConstant.INTERNAL_SERVER_ERROR);
        }
        return false;
    }

    @Override
    public String userLogin(UserDto userDto) {
        try {

            if (Objects.nonNull(userDto)) {

                UserMst userMst = userMstRepository.findByUsername(userDto.getUsername());
                if (Objects.isNull(userMst))
                    throw new ValidateException(MessageConstant.INVALID_USERNAME_OR_PASSWORD);

                Boolean isPasswordAuthenticated = utilityService.checkPassword(userMst.getUserPassword(), userDto.getPassword());
                if (!isPasswordAuthenticated)
                    throw new ValidateException(MessageConstant.INVALID_USERNAME_OR_PASSWORD);

                return utilityService.getUserTaken(userMst.getUsername(), userMst.getUserPassword());
            }

        } catch (ValidateException e) {
            throw new ValidateException(e.getMessage());
        } catch (Exception e) {
            log.error("User Login | Error | " + e.getMessage());
            throw new InternalServerException(MessageConstant.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @Override
    public Boolean validateUser(String username) {
        return userMstRepository.existsByUsername(username);
    }
}

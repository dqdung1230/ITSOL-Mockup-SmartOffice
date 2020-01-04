package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.EmployeeEntity;
import com.itsol.mockup.repository.UsersRepository;
import com.itsol.mockup.repository.UsersRepositoryCustom;
import com.itsol.mockup.services.UsersService;
import com.itsol.mockup.utils.Constants;
import com.itsol.mockup.utils.TokenUtils;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.request.auth.AuthRequestDTO;
import com.itsol.mockup.web.dto.response.GetListDataResponseDTO;
import com.itsol.mockup.web.dto.response.GetSingleDataResponseDTO;
import com.itsol.mockup.web.dto.response.auth.AuthResponseDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anhvd_itsol
 */

@Service
public class UsersServiceImpl implements UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersRepositoryCustom usersRepositoryCustom;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;


    @Override
    public GetListDataResponseDTO<UsersDTO> findAllUsers(SearchUsersRequestDTO request) {
        logger.info("=== START FIND ALL USERS::");
        GetListDataResponseDTO<UsersDTO> resultDTO = new GetListDataResponseDTO<>();
        List<UsersDTO> lstUsers = new ArrayList<>();
        try {
            Page<EmployeeEntity> rawDatas = usersRepository.findAll(PageRequest.of(request.getPage(), request.getPageSize()));
            if (rawDatas != null) {
                if (rawDatas.getContent().size() > 0) {
                    rawDatas.getContent().forEach(d -> {
                        UsersDTO usersDTO = modelMapper.map(d, UsersDTO.class);
                        lstUsers.add(usersDTO);
                    });
                }
                resultDTO.setResult(lstUsers, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("=== FIND ALL USERS RESPONSE::" + resultDTO.toString());
            }
        } catch (Exception ex) {
            resultDTO.setResult(null, null, null);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }

    @Override
    public GetListDataResponseDTO<UsersDTO> findUsersByFullNameAndUserName(SearchUsersRequestDTO requestDTO) {
        logger.info("=== START FIND ALL USERS BY FULL_NAME AND USER_NAME::");
        GetListDataResponseDTO<UsersDTO> resultDTO = new GetListDataResponseDTO<>();
        try {
            Page<UsersDTO> rawDatas = usersRepositoryCustom.findUsersByFullNameAndUserName(requestDTO);
            resultDTO.setResult(rawDatas.getContent(), rawDatas.getTotalElements(), rawDatas.getTotalPages());
            logger.info("=== FIND ALL USERS BY FULL_NAME AND USER_NAME RESPONSE::" + resultDTO.toString());
        } catch (Exception ex) {
            resultDTO.setResult(null, null, null);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }

    @Override
    public GetSingleDataResponseDTO<UsersDTO> addUser(UsersDTO requestDTO) {
        logger.info("=== START ADD NEW USER::");
        GetSingleDataResponseDTO<UsersDTO> resultDTO = new GetSingleDataResponseDTO<>();
        try {
            EmployeeEntity user = modelMapper.map(requestDTO, EmployeeEntity.class);
            user = usersRepository.save(user);
            if (user.getId() != null) {
                resultDTO.setResult(modelMapper.map(user, UsersDTO.class));
            } else {
                resultDTO.setResult(null);
            }
            logger.info("=== ADD NEW USER RESPONSE::" + resultDTO.toString());
        } catch (Exception ex) {
            resultDTO.setResult(null);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }

    @Override
    public GetSingleDataResponseDTO<UsersDTO> updateUser(UsersDTO usersDTO) {
        logger.info("=== START UPDATE USER::" + usersDTO.getId());
        GetSingleDataResponseDTO<UsersDTO> resultDTO = new GetSingleDataResponseDTO<>();
        try {
            EmployeeEntity user = usersRepository.getUsersById(usersDTO.getId());
            if (user.getId() != null) {
                user.setUsername(usersDTO.getUserName());
                user.setFullName(usersDTO.getFullName());
                user.setPassword(usersDTO.getPassWord());
//                user.setRoleEntities(usersDTO.getRole());
                user = usersRepository.save(user);
                resultDTO.setResult(modelMapper.map(user, UsersDTO.class));
            }
            logger.info("=== UPDATE USER RESPONSE::" + resultDTO.toString());
        } catch (Exception ex) {
            resultDTO.setResult(null);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }

    // ====== START SERVICES FOR AUTHENTICATION ======
    @Override
    public AuthResponseDTO generateToken(AuthRequestDTO userForAuthentication) {
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        logger.info("=== START GENERATE TOKEN::");
        try {
            if (isRequestDataValid(userForAuthentication)) {
                UserDetails springSecurityUser = userDetailsService.loadUserByUsername(userForAuthentication.getUsername());
                if (springSecurityUser != null &&
                        (springSecurityUser.getUsername().equals(userForAuthentication.getUsername()) &&
                                springSecurityUser.getPassword().equals(userForAuthentication.getPassword()))) {
                    responseDTO.setToken(tokenUtils.generateToken(springSecurityUser));
                    responseDTO.setUsername(springSecurityUser.getUsername());
                    responseDTO.setCode(Constants.ApiErrorCode.SUCCESS);
                    return responseDTO;
                }
            }
            responseDTO.setCode(Constants.ApiErrorCode.ERROR_401);
        } catch (Exception ex) {
            responseDTO.setCode(Constants.ApiErrorCode.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        logger.info("=== GENERATE TOKEN RESPONSE::" + responseDTO.toString());
        return responseDTO;
    }

    private boolean isRequestDataValid(AuthRequestDTO userForAuthentication) {
        return userForAuthentication != null &&
                userForAuthentication.getUsername() != null &&
                userForAuthentication.getPassword() != null &&
                !userForAuthentication.getUsername().isEmpty() &&
                !userForAuthentication.getPassword().isEmpty();
    }
    // ====== END ======


}

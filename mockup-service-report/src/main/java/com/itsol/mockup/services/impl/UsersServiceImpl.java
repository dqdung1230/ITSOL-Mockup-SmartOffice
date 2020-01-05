package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.EmployeeEntity;
import com.itsol.mockup.repository.EmployeeRepository;
import com.itsol.mockup.repository.EmployeeRepositoryCustom;
import com.itsol.mockup.services.UsersService;
import com.itsol.mockup.utils.Constants;
import com.itsol.mockup.utils.TokenUtils;
import com.itsol.mockup.web.dto.request.SearchEmployeeRequestDTO;
import com.itsol.mockup.web.dto.request.auth.AuthRequestDTO;
import com.itsol.mockup.web.dto.response.GetListDataResponseDTO;
import com.itsol.mockup.web.dto.response.GetSingleDataResponseDTO;
import com.itsol.mockup.web.dto.response.auth.AuthResponseDTO;
import com.itsol.mockup.web.dto.employees.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anhvd_itsol
 */

@Service
public class UsersServiceImpl implements UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRepositoryCustom employeeRepositoryCustom;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;


    @Override
    public GetListDataResponseDTO<EmployeeDTO> findAllUsers(SearchEmployeeRequestDTO request) {
        logger.info("=== START FIND ALL USERS::");
        GetListDataResponseDTO<EmployeeDTO> resultDTO = new GetListDataResponseDTO<>();
        List<EmployeeDTO> lstUsers = new ArrayList<>();
        try {
            Page<EmployeeEntity> rawDatas = employeeRepository.findAll(PageRequest.of(request.getPage(), request.getPageSize()));
            if (rawDatas != null) {
                if (rawDatas.getContent().size() > 0) {
                    rawDatas.getContent().forEach(d -> {
                        EmployeeDTO employeeDTO = modelMapper.map(d, EmployeeDTO.class);
                        lstUsers.add(employeeDTO);
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
    public GetListDataResponseDTO<EmployeeDTO> findUsersByFullNameAndUserName(SearchEmployeeRequestDTO requestDTO) {
        logger.info("=== START FIND ALL USERS BY FULL_NAME AND USER_NAME::");
        GetListDataResponseDTO<EmployeeDTO> resultDTO = new GetListDataResponseDTO<>();
        try {
            Page<EmployeeDTO> rawDatas = employeeRepositoryCustom.findUsersByFullNameAndUserName(requestDTO);
            resultDTO.setResult(rawDatas.getContent(), rawDatas.getTotalElements(), rawDatas.getTotalPages());
            logger.info("=== FIND ALL USERS BY FULL_NAME AND USER_NAME RESPONSE::" + resultDTO.toString());
        } catch (Exception ex) {
            resultDTO.setResult(null, null, null);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }

    @Override
    public GetSingleDataResponseDTO<EmployeeDTO> addUser(EmployeeDTO requestDTO) {
        logger.info("=== START ADD NEW USER::");
        GetSingleDataResponseDTO<EmployeeDTO> resultDTO = new GetSingleDataResponseDTO<>();
        try {
            EmployeeEntity user = modelMapper.map(requestDTO, EmployeeEntity.class);
            user = employeeRepository.save(user);
            if (user.getId() != null) {
                resultDTO.setResult(modelMapper.map(user, EmployeeDTO.class));
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
    public GetSingleDataResponseDTO<EmployeeDTO> updateUser(EmployeeDTO employeeDTO) {
        logger.info("=== START UPDATE USER::" + employeeDTO.getId());
        GetSingleDataResponseDTO<EmployeeDTO> resultDTO = new GetSingleDataResponseDTO<>();
        try {
            EmployeeEntity user = employeeRepository.getUsersById(employeeDTO.getId());
            if (user.getId() != null) {
                user.setUsername(employeeDTO.getUsername());
                user.setFullName(employeeDTO.getFullName());
                user.setPassword(employeeDTO.getPassword());
//                user.setRoleEntities(usersDTO.getRole());
                user = employeeRepository.save(user);
                resultDTO.setResult(modelMapper.map(user, EmployeeDTO.class));
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
                    responseDTO.setRoles(springSecurityUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
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

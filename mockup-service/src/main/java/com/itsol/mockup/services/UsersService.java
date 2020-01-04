package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.request.auth.AuthRequestDTO;
import com.itsol.mockup.web.dto.response.GetListDataResponseDTO;
import com.itsol.mockup.web.dto.response.GetSingleDataResponseDTO;
import com.itsol.mockup.web.dto.response.auth.AuthResponseDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author anhvd_itsol
 */

public interface UsersService {

    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    GetListDataResponseDTO<UsersDTO> findAllUsers(SearchUsersRequestDTO requestDTO);

    GetListDataResponseDTO<UsersDTO> findUsersByFullNameAndUserName(SearchUsersRequestDTO requestDTO);

    GetSingleDataResponseDTO<UsersDTO> addUser(UsersDTO usersDTO);

    GetSingleDataResponseDTO<UsersDTO> updateUser(UsersDTO usersDTO);

    // ====== START SERVICES FOR AUTHENTICATION ======
    AuthResponseDTO generateToken(AuthRequestDTO userForAuthentication);
    // ====== END ======
}

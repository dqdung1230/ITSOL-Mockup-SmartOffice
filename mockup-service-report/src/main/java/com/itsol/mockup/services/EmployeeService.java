package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.request.SearchEmployeeRequestDTO;
import com.itsol.mockup.web.dto.request.auth.AuthRequestDTO;
import com.itsol.mockup.web.dto.response.GetListDataResponseDTO;
import com.itsol.mockup.web.dto.response.GetSingleDataResponseDTO;
import com.itsol.mockup.web.dto.response.auth.AuthResponseDTO;
import com.itsol.mockup.web.dto.employees.EmployeeDTO;

/**
 * @author anhvd_itsol
 */

public interface EmployeeService {

    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    GetListDataResponseDTO<EmployeeDTO> findAllEmployees(SearchEmployeeRequestDTO requestDTO);

    GetListDataResponseDTO<EmployeeDTO> findByParams(SearchEmployeeRequestDTO requestDTO);

    GetSingleDataResponseDTO<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO);

    GetSingleDataResponseDTO<EmployeeDTO> updateUser(EmployeeDTO employeeDTO);

    // ====== START SERVICES FOR AUTHENTICATION ======
    AuthResponseDTO generateToken(AuthRequestDTO userForAuthentication);
    // ====== END ======
}

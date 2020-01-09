package com.itsol.mockup.repository;

import com.itsol.mockup.web.dto.request.SearchEmployeeRequestDTO;
import com.itsol.mockup.web.dto.employees.EmployeeDTO;
import org.springframework.data.domain.Page;

/**
 * @author anhvd_itsol
 */
public interface EmployeeRepositoryCustom {

    Page<EmployeeDTO> findUsersByFullNameAndUsername(SearchEmployeeRequestDTO requestDTO);
}

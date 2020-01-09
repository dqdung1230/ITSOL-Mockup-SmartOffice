package com.itsol.mockup.web.rest.employees;

import com.itsol.mockup.services.EmployeeService;
import com.itsol.mockup.web.dto.request.SearchEmployeeRequestDTO;
import com.itsol.mockup.web.dto.response.GetListDataResponseDTO;
import com.itsol.mockup.web.dto.response.GetSingleDataResponseDTO;
import com.itsol.mockup.web.dto.employees.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author anhvd_itsol
 */

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<GetListDataResponseDTO<EmployeeDTO>> findAllEmployees(@RequestBody SearchEmployeeRequestDTO requestDTO) {
        GetListDataResponseDTO<EmployeeDTO> result = employeeService.findAllEmployees(requestDTO);
        return new ResponseEntity<GetListDataResponseDTO<EmployeeDTO>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> addEmployee(@RequestBody EmployeeDTO requestDTO) {
        GetSingleDataResponseDTO<EmployeeDTO> result = employeeService.addEmployee(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> updateEmployee(@RequestBody EmployeeDTO requestDTO) {
        GetSingleDataResponseDTO<EmployeeDTO> result = employeeService.updateUser(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee-by-params", method = RequestMethod.POST)
    public ResponseEntity<GetListDataResponseDTO<EmployeeDTO>> findByParams(@RequestBody SearchEmployeeRequestDTO requestDTO){
        GetListDataResponseDTO<EmployeeDTO> result = employeeService.findByParams(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

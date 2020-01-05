package com.itsol.mockup.web.rest.users;

import com.itsol.mockup.services.UsersService;
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
@RequestMapping("/api")
@CrossOrigin
public class EmployeeController {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<GetListDataResponseDTO<EmployeeDTO>> users(@RequestBody SearchEmployeeRequestDTO requestDTO) {
        GetListDataResponseDTO<EmployeeDTO> result = usersService.findAllUsers(requestDTO);
        return new ResponseEntity<GetListDataResponseDTO<EmployeeDTO>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> addUser(@RequestBody EmployeeDTO requestDTO) {
        GetSingleDataResponseDTO<EmployeeDTO> result = usersService.addUser(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<GetSingleDataResponseDTO<EmployeeDTO>> updateUser(@RequestBody EmployeeDTO requestDTO) {
        GetSingleDataResponseDTO<EmployeeDTO> result = usersService.updateUser(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-fullname-username", method = RequestMethod.GET)
    public ResponseEntity<GetListDataResponseDTO<EmployeeDTO>> findByFullNameAndUserName (@RequestBody SearchEmployeeRequestDTO requestDTO){
        GetListDataResponseDTO<EmployeeDTO> result = usersService.findUsersByFullNameAndUserName(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

package com.itsol.mockup.repository;

import com.itsol.mockup.entity.EmployeeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

/**
 * @author anhvd_itsol
 */

@Repository
public interface UsersRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity getUsersById (Long id);
    Page<EmployeeEntity> getAllByUserNameAndFullName(String userName, String fullName, Pageable pageable);

    EmployeeEntity getUsersByUserName(String username); //function for generate token
}

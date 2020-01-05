package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.EmployeeEntity;
import com.itsol.mockup.entity.RoleEntity;
import com.itsol.mockup.repository.EmployeeRepository;
import com.itsol.mockup.web.dto.auth.SpringSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author anhvd_itsol
 */

@Service(value = "userDetailsService")
public class SpringUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity user = employeeRepository.getByUsername(username);
        if (user == null) {
            return null;
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            Set<RoleEntity> roleEntities = user.getRoleEntities();
            for (RoleEntity roleEntity : roleEntities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
            }
            return new SpringSecurityUser(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
    }
}

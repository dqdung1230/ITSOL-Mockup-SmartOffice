package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.EmployeeEntity;
import com.itsol.mockup.repository.UsersRepository;
import com.itsol.mockup.web.dto.auth.SpringSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author anhvd_itsol
 */

@Service(value="userDetailsService")
public class SpringUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity user = usersRepository.getUsersByUserName(username);
        if(user == null) {
            return null;
        }else {
            return new SpringSecurityUser(
                    user.getUsername(),
                    user.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
            );
        }
    }
}

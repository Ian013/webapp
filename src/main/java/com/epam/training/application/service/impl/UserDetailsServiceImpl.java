package com.epam.training.application.service.impl;

import com.epam.training.application.domain.User;
import com.epam.training.application.service.RoleService;
import com.epam.training.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;


    public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userService.getUserByEmail(email);
        Set<GrantedAuthority> roles = new HashSet<>();
        var userRoles = roleService.getRolesForUser(user.getId());
        for(var r:userRoles){
            roles.add(new SimpleGrantedAuthority(r.getName()));
        }

       return new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        roles);

    }

}

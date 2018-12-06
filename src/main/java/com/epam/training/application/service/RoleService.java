package com.epam.training.application.service;

import com.epam.training.application.domain.Role;

import java.util.List;

public interface RoleService extends BasicService<Role> {

    List<Role> getRolesForUser(int userId);
}

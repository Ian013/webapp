package com.epam.training.application.dao;

import com.epam.training.application.domain.Role;

import java.util.List;

public interface RoleDao extends BasicDao<Role> {


    List<Role> getRolesForUser(int userId);
}

package com.epam.training.application.service.impl;

import com.epam.training.application.dao.RoleDao;
import com.epam.training.application.domain.Role;
import com.epam.training.application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public Role getById(int id) {
        return roleDao.getById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Integer saveOrUpdate(Role role) {
        return roleDao.saveOrUpdate(role);
    }

    @Override
    public Integer remove(int id) {
        return roleDao.remove(id);
    }

    @Override
    public List<Role> getRolesForUser(int userId) {
        return roleDao.getRolesForUser(userId);
    }

    @Override
    public int setRoleForUser(int userId,int roleId) {
        return roleDao.setRoleForUser(userId,roleId);
    }
}

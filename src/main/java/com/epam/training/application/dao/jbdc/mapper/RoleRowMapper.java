package com.epam.training.application.dao.jbdc.mapper;

import com.epam.training.application.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt(1));
        role.setName(rs.getString(2));
        return role;
    }
}

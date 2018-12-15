package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.RoleDao;
import com.epam.training.application.dao.jbdc.mapper.RoleRowMapper;
import com.epam.training.application.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role getById(int id) {
        return jdbcTemplate.queryForObject("select * from role where role.id=?",
                new RoleRowMapper(),id);
    }

    @Override
    public List<Role> getAll() {
        return jdbcTemplate.query("select * from role",new RoleRowMapper());
    }

    @Override
    public Integer saveOrUpdate(Role role) {
        if(role.getId()>0){
            return jdbcTemplate.update("update role set name=? where id=?",role.getName(),role.getId());
        }else{
        return jdbcTemplate.update((c)->{
            PreparedStatement ps =c.prepareStatement("insert into role(name) values ?");
            ps.setString(1,role.getName());

            return ps;
        });
        }
    }

    @Override
    public Integer remove(int id) {
        return jdbcTemplate.update("delete from role where id = ?",id);
    }

    @Override
    public List<Role> getRolesForUser(int userId) {
        return jdbcTemplate.query("select role.id,role.name from role" +
                " join user_has_role uhr on role.id = uhr.role_id where uhr.user_id=?"
                ,new RoleRowMapper(),userId);
    }

    @Override
    public int setRoleForUser(int userId,int roleId) {
            return jdbcTemplate.update("insert into user_has_role(user_id, role_id)" +
                    " values (?,?)",userId,roleId);
    }
}

package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.TeacherDao;
import com.epam.training.application.dao.jbdc.mapper.TeacherRowMapper;
import com.epam.training.application.domain.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Transactional
@Repository
public class TeacherDaoImpl implements TeacherDao {

    private JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer saveOrUpdate(Teacher teacher) {
        KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO teacher(firstName, lastName) values (?, ?)";
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    @Override
    public Teacher getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM teacher WHERE teacher.id =?",
                new TeacherRowMapper(),id);
    }

    @Override
    public List<Teacher> getAll() {
        return jdbcTemplate.query("SELECT * FROM teacher",
                new TeacherRowMapper());
    }

    @Override
    public Integer remove(int id) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "DELETE FROM teacher WHERE teacher.id= ?";
        jdbcTemplate.update(sql,id,holder);
        return holder.getKey().intValue();
        //return holder.getKey().intValue();
    }
    @Override
    public List<Teacher> getByLastName(String lastName) {
        return jdbcTemplate.query("SELECT * FROM teacher WHERE teacher.lastName = ?",
                new TeacherRowMapper(),lastName);
    }
}

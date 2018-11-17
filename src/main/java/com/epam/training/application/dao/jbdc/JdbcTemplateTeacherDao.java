package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.TeacherDao;
import com.epam.training.application.dao.jbdc.mapper.TeacherRowMapper;
import com.epam.training.application.dao.model.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class JdbcTemplateTeacherDao implements TeacherDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateTeacherDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO teacher(firstName, lastName) values (?, ?)";
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            return ps;
        }, holder);

        return Integer.valueOf(holder.getKeys().get("teacher.id").toString());
    }

    @Override
    public Teacher getTeacher(long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM teacher WHERE teacher.id =?",
                new TeacherRowMapper(),id);
    }

    @Override
    public List<Teacher> getTeachers() {
        return jdbcTemplate.query("SELECT * FROM teacher",
                new TeacherRowMapper());
    }

    @Override
    public Integer updateTeacher(int id, Teacher teacher) {
        //TODO
        return null;
    }
}

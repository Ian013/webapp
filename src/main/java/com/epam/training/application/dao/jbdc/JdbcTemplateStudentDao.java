package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.StudentDao;
import com.epam.training.application.dao.jbdc.mapper.StudentRowMapper;
import com.epam.training.application.dao.model.Student;
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
public class JdbcTemplateStudentDao implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer addStudent(Student student) {
        KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO student(firstName, lastName) values (?, ?)";
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            return ps;
        }, holder);

        return holder.getKey().intValue();

    }

    @Override
    public Student getStudent(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM student WHERE student.id = ?"
                ,new StudentRowMapper(),id);
    }

    @Override
    public List<Student> getStudents() {
       return jdbcTemplate.query("SELECT * FROM student",new StudentRowMapper());
    }
}
/**/
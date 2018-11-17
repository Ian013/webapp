package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.CourseDao;
import com.epam.training.application.dao.jbdc.mapper.CourseRowMapper;
import com.epam.training.application.dao.model.Course;
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
public class JdbcTemplateCourseDao implements CourseDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCourseDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer addCourse(Course course) {
        KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO course(name, teacher_id, startDate, endDate) values (?, ?,?,?)";
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, course.getName());
            ps.setInt(2, course.getTeacherId());
            ps.setObject(3,course.getStartDate());
            ps.setObject(4,course.getEndDate());
            return ps;
        }, holder);

        return Integer.valueOf(holder.getKeys().get("course.id").toString());
    }

    @Override
    public Course getCourse(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT course.id,course.name,startDate,endDate,teacher.firstName,teacher.lastName " +
                        "FROM course JOIN teacher WHERE course.id = ? AND teacher_id=teacher.id"
                ,new CourseRowMapper(),id);
    }

    @Override
    public List<Course> getCourses() {
        return jdbcTemplate.query(
                "SELECT course.id,course.name,startDate,endDate,teacher.firstName,teacher.lastName" +
                " FROM course JOIN teacher WHERE teacher_id=teacher.id"
                ,new CourseRowMapper());
    }
}

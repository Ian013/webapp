package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.CourseDao;
import com.epam.training.application.dao.jbdc.mapper.CourseRowMapper;
import com.epam.training.application.domain.Course;
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
public class CourseDaoImpl implements CourseDao {
    private JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer saveOrUpdate(Course course) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO course(name, teacher_id, startDate, endDate) values (?, ?,?,?)";
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, course.getName());
            ps.setInt(2, course.getTeacher().getId());
            ps.setObject(3,course.getStartDate());
            ps.setObject(4,course.getEndDate());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    @Override
    public Integer remove(int id) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "DELETE FROM course WHERE course.id= ?;" +
                    " DELETE FROM user_has_course where course_id =?";
        jdbcTemplate.update(sql,id,id,holder);
        return holder.getKey().intValue();
    }

    @Override
    public Course getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT course.id,course.name,startDate,endDate,t.id,t.firstName,t.lastName " +
                        "FROM course JOIN teacher t WHERE course.id = ? AND teacher_id=t.id"
                ,new CourseRowMapper(),id);
    }

    @Override
    public List<Course> getAll() {
        return jdbcTemplate.query(
                "SELECT course.id,course.name,startDate,endDate,course.teacher_id,t.firstName,t.lastName" +
                        " FROM course JOIN teacher t WHERE course.teacher_id=t.id"
                ,new CourseRowMapper());
    }

    @Override
    public List<Course> getCoursesByStudentId(int studentId){
        return jdbcTemplate.query(
                "SELECT course.id, course.name, course.teacher_id,startDate,endDate,t.firstName,t.lastName" +
                " FROM course JOIN user_has_course shc on course.id = shc.course_id  " +
                        "join teacher t " +
                "JOIN user s on shc.user_id=s.id  WHERE s.id =? and course.teacher_id=t.id",
                new CourseRowMapper(),studentId);
    }
}

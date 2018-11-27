package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.StudentDao;
import com.epam.training.application.dao.jbdc.mapper.StudentRowMapper;
import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.Student;
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
public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer saveOrUpdate(Student student) {
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
    public Integer addCourse(int studentId,int courseId) {
        KeyHolder holder  =new GeneratedKeyHolder();
        jdbcTemplate.update((con)->{
            PreparedStatement pr =con.prepareStatement(
                    "INSERT INTO student_has_course(student_id, course_id) values (?,?)");
                        pr.setInt(1,studentId);
                        pr.setInt(2,courseId);
                        return pr;
            },holder);
        String sql ="INSERT INTO";
        return null;
    }

    @Override
    public Student getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM student WHERE student.id = ?"
                ,new StudentRowMapper(),id);
    }

    @Override
    public List<Student> getAll() {
       return jdbcTemplate.query("SELECT * FROM student",new StudentRowMapper());
    }

    @Override
    public List<Student> getStudentsFromCourse(int courseId) {
        return jdbcTemplate.query("SELECT student.id,student.firstName,student.lastName " +
                "FROM student JOIN student_has_course shc on student.id = shc.student_id WHERE shc.course_id=? "
                ,new StudentRowMapper(),courseId);
    }




    @Override
    public Integer remove(int id) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "DELETE FROM student_has_course" +
                        " WHERE student_id = ?;" +
                        "DELETE from student where id=?",id,id,holder);
        return holder.getKey().intValue();
        //return holder.getKey().intValue();
    }
}
/**/
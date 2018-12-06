package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.UserDao;
import com.epam.training.application.dao.jbdc.mapper.UserRowMapper;
import com.epam.training.application.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer saveOrUpdate(User user) {
        KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO user(firstName, lastName, registerDate, email, password, enabled) values (?,?, ?,?,?,?);"
                // "insert into user_has_role(user_id, role_id) VALUES (?,2)"
                ;
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setObject(3,new Date(System.currentTimeMillis()));
            ps.setString(4,user.getEmail());
            ps.setString(5,new BCryptPasswordEncoder().encode(user.getPassword()));
            ps.setBoolean(6,true);
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }
    @Override
    public Integer addCourse(int studentId,int courseId) {
        KeyHolder holder  =new GeneratedKeyHolder();
        jdbcTemplate.update((con)->{
            PreparedStatement pr =con.prepareStatement(
                    "INSERT INTO user_has_course(user_id, course_id) values (?,?)");
                        pr.setInt(1,studentId);
                        pr.setInt(2,courseId);
                        return pr;
            },holder);
        return null;
    }

    public Integer removeCourse(int studentId,int courseId){
        return null;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT user.id, firstName, lastName,registerDate, email, password, enabled  " +
                        "FROM user  WHERE user.id = ?"
                ,new UserRowMapper(),id);
    }

    @Override
    public List<User> getAll() {
       return jdbcTemplate.query("SELECT * FROM user",new UserRowMapper());
    }

    @Override
    public List<User> getStudentsFromCourse(int courseId) {
        return jdbcTemplate.query("SELECT user.id, firstName, lastName,registerDate, email, password, enabled  " +
                "FROM user JOIN user_has_course shc on user.id = shc.user_id " +
                        "WHERE shc.course_id=? "
                ,new UserRowMapper(),courseId);
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from user where user.email=?",
                new UserRowMapper(),email);
    }

    @Override
    public List<User> getAllStudents() {
        return jdbcTemplate.query("SELECT user.id, firstName, lastName,registerDate, email, password, enabled " +
                " from user join user_has_role uhr on user.id = uhr.user_id" +
                " where uhr.role_id = 2",new UserRowMapper());
    }

    @Override
    public List<User> getAllTeachers() {
        return jdbcTemplate.query("select user.id, firstName, lastName,registerDate, email, password, enabled from user" +
                " join user_has_role uhr on user.id = uhr.user_id " +
                        " where uhr.user_id=3"
                ,new UserRowMapper());
    }


    @Override
    public Integer remove(int id) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "DELETE FROM user_has_course" +
                        " WHERE user_id = ?;" +
                        "DELETE from user where id=?",id,id,holder);
        return holder.getKey().intValue();
    }
}
/**/
package com.epam.training.application.dao.jbdc.mapper;

import com.epam.training.application.domain.Archive;
import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import com.epam.training.application.domain.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ArchiveRowMapper  implements RowMapper<Archive> {

    @Override
    public Archive mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt(1);
        int note =rs.getInt(2);

        User user = new User(
                 rs.getInt(10)
                ,rs.getString(11)
                ,rs.getString(12));

        Course course = new Course(
                rs.getInt(5),
                rs.getString(6),
                rs.getDate(8),
                rs.getDate(9),
                new Teacher(
                        rs.getInt(13),
                        rs.getString(14),
                        rs.getString(15)
        ));

        return new Archive(id,note, user,course);
    }
}

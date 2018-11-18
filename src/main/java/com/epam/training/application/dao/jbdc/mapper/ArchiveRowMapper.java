package com.epam.training.application.dao.jbdc.mapper;

import com.epam.training.application.dao.model.Archive;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ArchiveRowMapper  implements RowMapper<Archive> {

    @Override
    public Archive mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        int note =resultSet.getInt("note");
        int studentId = resultSet.getInt("student_id");
        int courseId = resultSet.getInt("course_id");
        return new Archive(id,note,studentId,courseId);
    }
}

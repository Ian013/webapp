package com.epam.training.application.dao.jbdc.mapper;

import com.epam.training.application.domain.Archive;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ArchiveRowMapper  implements RowMapper<Archive> {

    @Override
    public Archive mapRow(ResultSet rs, int i) throws SQLException {

        return new Archive(rs.getInt("id"),
                rs.getInt("note"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getDate("date"));
    }
}

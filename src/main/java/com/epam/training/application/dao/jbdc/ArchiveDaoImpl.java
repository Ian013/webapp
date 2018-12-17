package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.ArchiveDao;
import com.epam.training.application.dao.jbdc.mapper.ArchiveRowMapper;
import com.epam.training.application.domain.Archive;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Transactional
@Repository
public class ArchiveDaoImpl implements ArchiveDao {

    private JdbcTemplate jdbcTemplate;

    public ArchiveDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Integer saveOrUpdate(Archive archive) {
        String sql;
       if(archive.getStudentId()>0&&archive.getCourseId()>0){
           sql="update archive set note=?,date=? where student_id=? and course_id=?";
       }else {
           sql = "INSERT INTO archive(note,date,student_id, course_id) values (?, ?,?,?)";
       }
       return jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, archive.getNote());
            ps.setDate(2,new Date(System.currentTimeMillis()));
            ps.setInt(3, archive.getStudentId());
            ps.setObject(4,archive.getCourseId());
            return ps;
        });

    }

    @Override
    public Archive getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM archive " +
                        "WHERE id = ?",
                new ArchiveRowMapper(),id);
    }
    @Override
    public List<Archive> getArchiveNotesForStudent(int studentId) {
        return jdbcTemplate.query(
                    "SELECT * FROM archive " +
                        "where student_id =?",
                new ArchiveRowMapper(),studentId);
    }

    @Override
    public Integer setMarkForStudent(int courseId, int studentId, int mark) {
        return jdbcTemplate.update(
                "INSERT INTO archive(note, student_id, course_id,date)" +
                " values (?,?,?,?)",mark,studentId,courseId,new Date(System.currentTimeMillis()));
    }

    @Override
    public List<Archive> getMarksForStudent(int courseId, int studentId){
     return jdbcTemplate.query("SELECT * FROM archive WHERE archive.course_id=?" +
                " and archive.student_id=? ",new ArchiveRowMapper(),courseId,studentId);
    }


    @Override
    public Integer remove(int id) {
        String sql = "DELETE FROM archive WHERE archive.id=?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public List<Archive> getAll() {
        return jdbcTemplate.query(
                    "SELECT * FROM archive",
                new ArchiveRowMapper());
    }
}
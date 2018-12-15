package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.ArchiveDao;
import com.epam.training.application.dao.jbdc.mapper.ArchiveRowMapper;
import com.epam.training.application.domain.Archive;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
       if(archive.getUser().getId()>0&&archive.getCourse().getId()>0){
           sql="update archive set note=? where student_id=? and course_id=?";
       }else {
           sql = "INSERT INTO archive(note,student_id, course_id) values (?, ?,?)";
       }
       return jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, archive.getNote());
            ps.setInt(2, archive.getUser().getId());
            ps.setObject(3,archive.getCourse().getId());
            return ps;
        });

    }

    @Override
    public Archive getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM " +
                        "archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN user s on archive.student_id = s.id " +
                        "JOIN user t on c2.teacher_id = t.id" +
                        " WHERE student_id =?",
                new ArchiveRowMapper(),id);
    }
    @Override
    public List<Archive> getArchiveNotesForStudent(int studentId) {
        return jdbcTemplate.query(
                    "SELECT * FROM archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN user s on archive.student_id = s.id " +
                        "JOIN user t on c2.teacher_id = t.id " +
                        "WHERE s.id =?",
                new ArchiveRowMapper(),studentId);
    }

    @Override
    public Integer setMarkForStudent(int courseId, int studentId, int mark) {
        return jdbcTemplate.update(
                "INSERT INTO archive(note, student_id, course_id)" +
                " values (?,?,?)",mark,studentId,courseId);
    }

    @Override
    public int getMarkForStudent(int courseId, int studentId) {
        Archive a=jdbcTemplate.queryForObject("SELECT * FROM archive WHERE archive.course_id=?" +
                " and archive.student_id=? ",new ArchiveRowMapper(),courseId,studentId);
        return a.getNote();
    }


    @Override
    public Integer remove(int id) {
        String sql = "DELETE FROM archive WHERE archive.id= ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public List<Archive> getAll() {
        return jdbcTemplate.query(
                    "SELECT * FROM archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN user s on archive.student_id = s.id " +
                        "JOIN user t on c2.teacher_id = t.id",
                new ArchiveRowMapper());
    }
}
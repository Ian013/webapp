package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.ArchiveDao;
import com.epam.training.application.dao.jbdc.mapper.ArchiveRowMapper;
import com.epam.training.application.domain.Archive;
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
public class ArchiveDaoImpl implements ArchiveDao {

    private JdbcTemplate jdbcTemplate;

    public ArchiveDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Integer saveOrUpdate(Archive archive) {
        KeyHolder holder = new GeneratedKeyHolder();

        String sql = "INSERT INTO archive(note, user_id, course_id) values (?, ?,?)";
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, archive.getNote());
            ps.setInt(2, archive.getUser().getId());
            ps.setObject(3,archive.getCourse().getId());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    @Override
    public Archive getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN user s on archive.student_id = s.id " +
                        "JOIN teacher on c2.teacher_id = teacher.id" +
                        " WHERE archive.id =?",
                new ArchiveRowMapper(),id);
    }
    @Override
    public Archive getArchiveNoteForStudent(int studentId) {
        return jdbcTemplate.queryForObject(
                    "SELECT * FROM archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN user s on archive.student_id = s.id " +
                        "JOIN teacher t on c2.teacher_id = t.id" +
                        "WHERE s.id =?",
                new ArchiveRowMapper(),studentId);
    }

    @Override
    public Integer remove(Archive archive) {
        return null;
    }

    @Override
    public Integer remove(int id) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "DELETE FROM archive WHERE archive.id= ?";
        jdbcTemplate.update(sql,id,holder);
        return holder.getKey().intValue();
    }

    @Override
    public List<Archive> getAll() {
        return jdbcTemplate.query(
                    "SELECT * FROM archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN user s on archive.student_id = s.id " +
                        "JOIN teacher t on c2.teacher_id = t.id",
                new ArchiveRowMapper());
    }
}
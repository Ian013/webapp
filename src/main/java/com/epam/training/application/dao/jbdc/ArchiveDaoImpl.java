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

        String sql = "INSERT INTO archive(note, student_id, course_id) values (?, ?,?)";
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, archive.getNote());
            ps.setInt(2, archive.getStudentId());
            ps.setObject(3,archive.getCourseId());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    @Override
    public Integer remove(int id) {
        return null;
    }

    @Override
    public Archive getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT archive.id,archive.note,c2.name,s.firstName,s.lastName,t.lastName FROM archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN student s on archive.student_id = s.id " +
                        "JOIN teacher t on c2.teacher_id = t.id WHERE archive.id =?",new ArchiveRowMapper(),id);
    }
    @Override
    public Archive getArchiveNoteForStudent(int studentId) {
        return jdbcTemplate.queryForObject(
                "SELECT s.id,archive.note,c2.name,s.firstName,s.lastName,t.lastName FROM archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN student s on archive.student_id = s.id " +
                        "JOIN teacher t on c2.teacher_id = t.id WHERE s.id =?",new ArchiveRowMapper(),studentId);
    }

    @Override
    public Integer remove(Archive archive) {
        return null;
    }

    @Override
    public List<Archive> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM archive ",new ArchiveRowMapper());
    }
}/*"SELECT archive.id,archive.note,c2.name,s.firstName,s.lastName,t.lastName FROM archive " +
                        "JOIN course c2 on archive.course_id = c2.id " +
                        "JOIN student s on archive.student_id = s.id " +
                        "JOIN teacher t on c2.teacher_id = t.id"*/
package com.epam.training.application.dao.jbdc;

import com.epam.training.application.dao.ArchiveDao;
import com.epam.training.application.dao.model.Archive;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class JdbcTemplateArchiveDao implements ArchiveDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateArchiveDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Integer addArchive() {
        return null;
    }

    @Override
    public Archive gerArchive(int id) {
        return null;
    }

    @Override
    public List<Archive> getArchives() {
        return null;
    }
}

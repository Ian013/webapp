package com.epam.training.application.dao;

import com.epam.training.application.dao.model.Archive;

import java.util.List;

public interface ArchiveDao {

    Integer addArchive(Archive archive);

    Archive getArchive(int id);

    Archive getArchiveNoteForStudent(int studentId);

    List<Archive> getArchives();
}

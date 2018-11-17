package com.epam.training.application.dao;

import com.epam.training.application.dao.model.Archive;

import java.util.List;

public interface ArchiveDao {

    Integer addArchive();

    Archive gerArchive(int id);

    List<Archive> getArchives();
}

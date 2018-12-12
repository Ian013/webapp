package com.epam.training.application.dao;

import com.epam.training.application.domain.Archive;

import java.util.List;

public interface ArchiveDao extends BasicDao<Archive> {

    Archive getArchiveNoteForStudent(int studentId);
    Integer setMarkForStudent(int courseId,int studentId,int mark);


    Integer remove(int archiveId);
}

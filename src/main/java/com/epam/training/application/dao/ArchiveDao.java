package com.epam.training.application.dao;

import com.epam.training.application.domain.Archive;

import java.util.List;

public interface ArchiveDao extends BasicDao<Archive> {

    List<Archive> getArchiveNotesForStudent(int studentId) ;

    Integer setMarkForStudent(int courseId,int studentId,int mark);

    List<Archive> getMarksForStudent(int courseId, int studentId);

    Integer remove(int archiveId);
}

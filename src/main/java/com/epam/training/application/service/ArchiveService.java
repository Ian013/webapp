package com.epam.training.application.service;

import com.epam.training.application.domain.Archive;

import java.util.List;

public interface ArchiveService extends BasicService<Archive> {
    Integer setMarkForStudent(int courseId,int studentId,int mark);
    List<Archive> getArchiveNotesForStudent(int studentId);
//int getMarkF
}

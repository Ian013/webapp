package com.epam.training.application.service;

import com.epam.training.application.domain.Archive;

public interface ArchiveService extends BasicService<Archive> {
    Integer setMarkForStudent(int courseId,int studentId,int mark);
    Archive getArchiveNoteForStudent(int studentId);

}

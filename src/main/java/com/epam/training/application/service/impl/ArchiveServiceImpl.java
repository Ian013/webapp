package com.epam.training.application.service.impl;

import com.epam.training.application.dao.ArchiveDao;
import com.epam.training.application.domain.Archive;
import com.epam.training.application.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArchiveServiceImpl implements ArchiveService {

    private final ArchiveDao archiveDao;

    @Autowired
    public ArchiveServiceImpl(ArchiveDao archiveDao) {
        this.archiveDao = archiveDao;
    }

    @Override
    public Archive getById(int id) {
        if(id >0){
            return archiveDao.getById(id);
        }
        return null;
    }

    @Override
    public List<Archive> getAll() {
        return archiveDao.getAll();
    }

    @Override
    public Integer saveOrUpdate(Archive archive) {
        if(archive!=null){
            return archiveDao.saveOrUpdate(archive);
        }throw new NullPointerException();
    }

    @Override
    public Integer remove(int id) {
        return archiveDao.remove(id);
    }
}

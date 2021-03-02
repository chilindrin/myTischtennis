package org.chilin.db.service;

import org.chilin.db.model.TTRHistory;
import org.chilin.db.repository.TTRHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TTRHistoryService implements ITTRHistoryService {

    @Autowired
    private TTRHistoryRepository ttrHistoryRepository;

    @Override
    public List<TTRHistory> findAll() {
        return (List<TTRHistory>) ttrHistoryRepository.findAll();
    }
}

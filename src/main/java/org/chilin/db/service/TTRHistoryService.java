package org.chilin.db.service;

import org.chilin.db.model.TTRHistory;
import org.chilin.db.repository.TTRHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TTRHistoryService implements ITTRHistoryService {

    @Autowired
    private TTRHistoryRepository ttrHistoryRepository;

    @Override
    public List<TTRHistory> findAll() {
        return (List<TTRHistory>) ttrHistoryRepository.findAll();
    }

    public Long insert(TTRHistory ttrHistory){
        TTRHistory save = ttrHistoryRepository.save(ttrHistory);
        return save.getId();
    }

    public void deleteHistoryIfNecessary(Long historyId){
        Optional<TTRHistory> ttrHistoryOptional = ttrHistoryRepository.findById(historyId);
        if (ttrHistoryOptional.isPresent()){
            ttrHistoryRepository.deleteById(historyId);
        }
    }

    public TTRHistory getLastTtrHistory(){
        Pageable pageable = PageRequest.of(0,1, Sort.by(Sort.Order.desc("fecha")));
        Page<TTRHistory> allLastTtrHistory = ttrHistoryRepository.findByFecha(pageable);
        Iterator<TTRHistory> resultsIterator = allLastTtrHistory.iterator();
        return resultsIterator.next();
    }

}

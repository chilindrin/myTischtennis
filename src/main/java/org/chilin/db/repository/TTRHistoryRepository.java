package org.chilin.db.repository;

import org.chilin.db.model.TTRHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TTRHistoryRepository extends CrudRepository<TTRHistory, Long> {
}

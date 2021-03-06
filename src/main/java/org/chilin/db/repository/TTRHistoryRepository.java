package org.chilin.db.repository;

import org.chilin.db.model.TTRHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TTRHistoryRepository extends CrudRepository<TTRHistory, Long> {

    @Query("SELECT h FROM TTRHistory h")
    Page<TTRHistory> findByFecha(Pageable pageable);

}

package br.com.nexus.commons.interfaces.datasource;

import br.com.nexus.commons.dao.EventDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventDataSource extends JpaRepository<EventDAO, UUID> {

    @Query(value = """
            SELECT e.*
            FROM tb_event e
            WHERE e.producer_id = :id
        """, nativeQuery = true)
    List<EventDAO> findAllByProducerId(UUID id);
}

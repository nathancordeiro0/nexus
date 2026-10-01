package br.com.nexus.commons.interfaces.datasource;

import br.com.nexus.commons.dao.TicketDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketDataSource extends JpaRepository<TicketDAO, UUID> {

    @Query(value = """
                SELECT p.*
                FROM tb_order_item oi
                JOIN tb_ticket p ON oi.ticket_id = p.id
                WHERE oi.id = :id
            """, nativeQuery = true)
    Optional<TicketDAO> findByOrderItemId(UUID id);
}

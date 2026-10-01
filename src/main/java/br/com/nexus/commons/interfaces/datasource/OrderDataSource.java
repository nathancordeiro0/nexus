package br.com.nexus.commons.interfaces.datasource;

import br.com.nexus.commons.dao.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDataSource extends JpaRepository<OrderDAO, UUID> {
}

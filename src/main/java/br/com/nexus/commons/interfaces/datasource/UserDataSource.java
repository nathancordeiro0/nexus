package br.com.nexus.commons.interfaces.datasource;

import br.com.nexus.commons.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataSource extends JpaRepository<UserDAO, UUID> {

    Optional<UserDAO> findByEmail(String email);

}

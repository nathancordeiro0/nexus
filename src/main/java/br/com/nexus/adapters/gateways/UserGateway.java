package br.com.nexus.adapters.gateways;

import br.com.nexus.adapters.presenters.UserMapper;
import br.com.nexus.commons.interfaces.datasource.UserDataSource;
import br.com.nexus.commons.interfaces.gateways.UserGatewayInterface;
import br.com.nexus.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserGateway implements UserGatewayInterface {

    private final UserDataSource userDataSource;
    private final UserMapper mapper;

    @Override
    public User findById(UUID id) {
        return mapper.fromDaoToEntity(userDataSource.findById(id)
                .orElse(null));
    }

    @Override
    public List<User> findAll() {
        return userDataSource.findAll()
                .stream()
                .map(mapper::fromDaoToEntity)
                .toList();
    }

    @Override
    public User findByEmail(String email) {
        return mapper.fromDaoToEntity(userDataSource.findByEmail(email)
                .orElse(null));
    }

    @Override
    public User save(User user) {
        var request = mapper.fromEntityToDao(user);

        var newUser = userDataSource.save(request);

        return mapper.fromDaoToEntity(newUser);
    }

    @Override
    public void delete(UUID id) {
        var userToDelete = findById(id);

        userDataSource.delete(mapper.fromEntityToDao(userToDelete));
    }

    @Override
    public void update(User user) {
        var existingUser = findById(user.getId());

        mapper.updateEntity(user, existingUser);

        var newUser = mapper.fromEntityToDao(existingUser);

        userDataSource.save(newUser);
    }

}

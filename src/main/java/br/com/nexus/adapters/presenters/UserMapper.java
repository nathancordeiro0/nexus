package br.com.nexus.adapters.presenters;

import br.com.nexus.commons.dao.UserDAO;
import br.com.nexus.commons.dto.request.user.CreateUserRequestV1;
import br.com.nexus.commons.dto.request.user.UpdateUserRequestV1;
import br.com.nexus.commons.dto.response.user.UserResponseV1;
import br.com.nexus.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDAO fromEntityToDao(User entity);

    User fromDaoToEntity(UserDAO dao);

    UserResponseV1 fromEntityToResponse(User entity);

    User fromRequestToEntity(CreateUserRequestV1 request);

    User fromUpdateRequestToEntity(UpdateUserRequestV1 request);

    @Mapping(target = "password", ignore = true)
    void updateEntity(User request, @MappingTarget User entity);

}

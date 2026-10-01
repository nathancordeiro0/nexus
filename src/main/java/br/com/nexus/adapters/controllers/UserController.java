package br.com.nexus.adapters.controllers;

import br.com.nexus.adapters.presenters.UserMapper;
import br.com.nexus.commons.config.CustomUserDetails;
import br.com.nexus.commons.dto.request.user.UpdateUserRequestV1;
import br.com.nexus.commons.dto.response.user.UserResponseV1;
import br.com.nexus.usecases.user.DeleteUserByIdUseCase;
import br.com.nexus.usecases.user.GetAllUsersUseCase;
import br.com.nexus.usecases.user.GetUserByIdUseCase;
import br.com.nexus.usecases.user.UpdateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final GetUserByIdUseCase getUserById;
    private final GetAllUsersUseCase getAllUsers;
    private final DeleteUserByIdUseCase deleteUserById;
    private final UpdateUserUseCase updateUser;
    private final UserMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseV1> getById(@PathVariable UUID userId) {
        log.debug("find request id: {}", userId);

        var user = getUserById.execute(userId);

        var response = mapper.fromEntityToResponse(user);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseV1>> getAll() {
        var usersList = getAllUsers.execute();

        var response = usersList.stream().map(mapper::fromEntityToResponse).toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId) {
        log.debug("delete request id: {}", userId);

        deleteUserById.execute(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/users/profile")
    public ResponseEntity<Void> update(
            @RequestBody @Valid UpdateUserRequestV1 request,
            Authentication auth
    ) {
        log.debug("update request: {}", request);

        CustomUserDetails  userDetails = (CustomUserDetails) auth.getPrincipal();
        UUID userId = userDetails.getUserId();

        var newUser = mapper.fromUpdateRequestToEntity(request);

        updateUser.execute(userId, newUser);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }
}

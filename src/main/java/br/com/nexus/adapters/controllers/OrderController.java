package br.com.nexus.adapters.controllers;

import br.com.nexus.adapters.presenters.OrderMapper;
import br.com.nexus.commons.config.CustomUserDetails;
import br.com.nexus.commons.dto.request.order.CreateOrderRequestV1;
import br.com.nexus.commons.dto.response.order.OrderResponseV1;
import br.com.nexus.usecases.order.CreateOrderUseCase;
import br.com.nexus.usecases.order.GetOrderByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final GetOrderByIdUseCase getOrderById;
    private final CreateOrderUseCase createOrder;
    private final OrderMapper mapper;

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderResponseV1> getById(@PathVariable UUID id) {
        log.debug("find request id: {}", id);

        var order = getOrderById.execute(id);

        var response = mapper.fromEntityToResponse(order);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseV1> save(
            @RequestBody @Valid CreateOrderRequestV1 request,
            Authentication auth
    ) {
        log.debug("create request: {}", request);

        CustomUserDetails userDetails = (CustomUserDetails)auth.getPrincipal();
        UUID customerId = userDetails.getUserId();

        var newOrder = mapper.fromRequestToEntity(request);

        var savedOrder = createOrder.execute(customerId, newOrder);

        var response = mapper.fromEntityToResponse(savedOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

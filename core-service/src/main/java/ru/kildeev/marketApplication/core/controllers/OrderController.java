package ru.kildeev.marketApplication.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kildeev.marketApplication.core.api.OrderDto;
import ru.kildeev.marketApplication.core.services.OrderService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getMyOrders(Principal principal) {
        return orderService.getByUsername(principal.getName());
    }
}

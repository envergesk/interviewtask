package ru.kildeev.marketApplication.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kildeev.marketApplication.core.api.OrderDto;
import ru.kildeev.marketApplication.core.converters.OrderConverter;
import ru.kildeev.marketApplication.core.entities.Order;
import ru.kildeev.marketApplication.core.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<OrderDto> getByUsername(String username) {
        return orderRepository.findByUsername(username).stream().map(order -> orderConverter.entityToDto(order)).collect(Collectors.toList());
    }


}

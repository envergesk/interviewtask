package ru.kildeev.marketApplication.core.converters;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import ru.kildeev.marketApplication.core.api.OrderDto;
import ru.kildeev.marketApplication.core.entities.Order;

@Component
public class OrderConverter {

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
//        orderDto.setUsername(order.getUsername());
//        orderDto.setProduct(order.getProduct());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setDiscount(order.getDiscount());
        return orderDto;
    }

    public Order dtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setUsername(order.getUsername());
        order.setProduct(order.getProduct());
        order.setDiscount(orderDto.getDiscount());
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }
}

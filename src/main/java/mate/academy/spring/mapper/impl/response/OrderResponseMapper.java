package mate.academy.spring.mapper.impl.response;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.mapper.DtoResponseMapper;
import mate.academy.spring.model.Order;
import mate.academy.spring.model.Ticket;
import mate.academy.spring.model.dto.response.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseMapper implements DtoResponseMapper<OrderResponseDto, Order> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate().format(formatter));
        orderResponseDto.setUserId(order.getUser().getId());
        List<Long> tickets = order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        orderResponseDto.setTicketIds(tickets);
        return orderResponseDto;
    }
}
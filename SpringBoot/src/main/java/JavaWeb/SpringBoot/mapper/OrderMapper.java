package JavaWeb.SpringBoot.mapper;

import JavaWeb.SpringBoot.dto.response.OrderResponseDTO;
import JavaWeb.SpringBoot.entity.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public List<OrderResponseDTO> convertEntityToResponseDtos(List<Order> orderList){
        return orderList.stream().map(this:: convertEntityToResponseDto).toList();
    }

    public OrderResponseDTO convertEntityToResponseDto(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        BeanUtils.copyProperties( order, orderResponseDTO);
        return orderResponseDTO;
    }
}

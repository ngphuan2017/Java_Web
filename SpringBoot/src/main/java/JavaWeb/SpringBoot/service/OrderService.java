package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedOrderDTO;
import JavaWeb.SpringBoot.dto.request.UpdateOrderDTO;
import JavaWeb.SpringBoot.dto.response.OrderResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();
    PageResponseDTO getOrderPaging();
    Order createdOrder(CreatedOrderDTO createdOrderDTO);
    OrderResponseDTO getOrderById(Integer id);

    OrderResponseDTO updateOrder(UpdateOrderDTO requestDTO, Integer id);

    void deleteOrderById(Integer id);
}

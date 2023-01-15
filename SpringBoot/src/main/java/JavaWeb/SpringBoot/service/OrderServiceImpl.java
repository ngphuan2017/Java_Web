package JavaWeb.SpringBoot.service;

import JavaWeb.SpringBoot.dto.request.CreatedOrderDTO;
import JavaWeb.SpringBoot.dto.request.UpdateOrderDTO;
import JavaWeb.SpringBoot.dto.response.OrderResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Order;
import JavaWeb.SpringBoot.mapper.OrderMapper;
import JavaWeb.SpringBoot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> getAllOrder() {
        Iterable<Order> orderIterable = this.orderRepository.findAll();
        return (List)orderIterable;
    }
    @Override
    public PageResponseDTO getOrderPaging() {
        Pageable pageable = Pageable.ofSize(2).withPage(0);

        Page<Order> orderPage = orderRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Error"));
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(orderPage.getNumber());
        pageResponseDTO.setSize(orderPage.getSize());
        pageResponseDTO.setTotalPages(orderPage.getTotalPages());
        pageResponseDTO.setTotalRecord(orderPage.getTotalElements());
        List<OrderResponseDTO> orderResponseDTOS = orderMapper.convertEntityToResponseDtos(orderPage.getContent());
        pageResponseDTO.setData(orderResponseDTOS);
        return pageResponseDTO;
    }
    @Override
    @Transactional
    public Order createdOrder(CreatedOrderDTO createdOrderDTO) {
        Order order = new Order();
        order.setTotalPrice(createdOrderDTO.getTotalPrice());
        order.setStatus(createdOrderDTO.getStatus());
        this.orderRepository.save(order);
        return order;
    }
    @Override
    public OrderResponseDTO getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        OrderResponseDTO orderResponseDTOS = orderMapper.convertEntityToResponseDto(order);
        return orderResponseDTOS;
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(UpdateOrderDTO requestDTO, Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));
        order.setTotalPrice(requestDTO.getTotalPrice());
        order.setStatus(requestDTO.getStatus());
        order.setCartId(requestDTO.getCartId());
        orderRepository.save(order);
        OrderResponseDTO orderResponseDTO = orderMapper.convertEntityToResponseDto(order);
        return orderResponseDTO;
    }
    @Override
    @Transactional
    public void deleteOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));

        orderRepository.delete(order);
    }
}

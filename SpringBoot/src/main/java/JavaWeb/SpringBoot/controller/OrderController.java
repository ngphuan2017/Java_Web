package JavaWeb.SpringBoot.controller;

import JavaWeb.SpringBoot.dto.request.CreatedOrderDTO;
import JavaWeb.SpringBoot.dto.request.UpdateOrderDTO;
import JavaWeb.SpringBoot.dto.response.OrderResponseDTO;
import JavaWeb.SpringBoot.dto.response.PageResponseDTO;
import JavaWeb.SpringBoot.entity.Order;
import JavaWeb.SpringBoot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public ResponseEntity<?> getAllOrder(){
        List<Order> orderList = this.orderService.getAllOrder();
        return new ResponseEntity(orderList, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getOrderPaging() {
        PageResponseDTO pageResponseDTO = orderService.getOrderPaging();
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<?> getOrderById(@PathVariable(value = "order-id") int orderId) {
        OrderResponseDTO OrderResponseDTO = orderService.getOrderById(orderId);
        return new ResponseEntity<>(OrderResponseDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity insertOrder(@RequestBody CreatedOrderDTO createdOrderDTO){
        Order order = this.orderService.createdOrder(createdOrderDTO);
        return new ResponseEntity(order, HttpStatus.OK);
    }
    @PutMapping("/{order-id}")
    public ResponseEntity updateOrder(@PathVariable(value = "order-id") int orderId,
                                      @RequestBody UpdateOrderDTO updateOrderRequestDTO) {
        OrderResponseDTO response = orderService.updateOrder(updateOrderRequestDTO, orderId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable(value = "order-id") int orderId) {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

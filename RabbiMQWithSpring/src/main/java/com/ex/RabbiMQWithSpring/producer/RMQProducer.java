package com.ex.RabbiMQWithSpring.producer;


import com.ex.RabbiMQWithSpring.config.RabbitMQConfig;
import com.ex.RabbiMQWithSpring.entity.Order;
import com.ex.RabbiMQWithSpring.entity.OrderDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/order")
    public OrderDTO placeOrder(@RequestBody Order order) {
        System.out.println("Order Object is"+order.toString());
        // Create OrderDTO response
        OrderDTO orderDTO = new OrderDTO(order, "Order Placed", "Hi Producer, Your Order is Placed");
        System.out.println("Constructed OrderDTO: " + orderDTO.toString());

       // orderDTO.setSomeField("value");
        // Send to RabbitMQ exchange with routing key
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, orderDTO);
        return orderDTO;
    }


}

package com.ex.RabbiMQWithSpring.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class OrderDTO {
    private Order order;
    private String orderStatus;
    private String message;


    public OrderDTO(Order order, String orderStatus, String message) {
        this.order = order;
        this.orderStatus = orderStatus;
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "order=" + order +
                ", orderStatus='" + orderStatus + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

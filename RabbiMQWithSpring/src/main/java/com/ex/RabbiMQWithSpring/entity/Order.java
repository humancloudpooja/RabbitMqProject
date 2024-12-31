package com.ex.RabbiMQWithSpring.entity;


import lombok.*;


@NoArgsConstructor
@Setter
@Getter

public class Order {
    private Integer id ;
    private String name ;
    private Double price ;
    private Integer qty ;

    public Order(Integer id, String name, Double price, Integer qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}

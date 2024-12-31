package com.ex.RabbiMQWithSpring.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "rabbit_mq_queue";
    public static final String EXCHANGE = "rabbit_mq_exchange";
    public static final String ROUTING_KEY = "rabbit_mq_r_key";

    /**
     * Creates a queue with the specified name.
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true); // Durable queue
    }

    /**
     * Creates a Direct Exchange with the specified name.
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE);
    }

    /**
     * Binds the queue to the direct exchange using the specified routing key.
     */
    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY);
    }

    /**
     * Configures the message converter to use JSON serialization for messages.
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configures the RabbitTemplate with a custom message converter.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        // Optional: Set timeouts or other properties
        rabbitTemplate.setReplyTimeout(5000); // 5 seconds reply timeout
        rabbitTemplate.setReceiveTimeout(5000); // 5 seconds receive timeout

        return rabbitTemplate;
    }

    /**
     * Creates an AmqpTemplate using the configured RabbitTemplate.
     */
    @Bean
    public AmqpTemplate amqpTemplate(RabbitTemplate rabbitTemplate) {
        return rabbitTemplate;
    }
}

package com.felipesousa.cupomminer.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.AllowedListDeserializingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "coupon-exchange";
    public static final String QUEUE_NAME = "coupon-queue";
    public static final String ROUTING_KEY = "coupon.routingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverterJackson() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AllowedListDeserializingMessageConverter messageConverter() {
        AllowedListDeserializingMessageConverter converter = new AllowedListDeserializingMessageConverter() {
            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return null;
            }

            @Override
            protected Message createMessage(Object o, MessageProperties messageProperties) {
                return null;
            }
        };
        converter.addAllowedListPatterns("com.felipesousa.cupomminer.entities.Coupon");
        return converter;
    }
}

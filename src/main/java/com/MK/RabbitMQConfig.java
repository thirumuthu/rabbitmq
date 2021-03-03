package com.MK;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.user}")
    String userName;
    @Value("${spring.rabbitmq.pwd}")
    String pwd;
    @Value("${spring.rabbitmq.host}")
    String host;
    @Value("${spring.rabbitmq.inputQueue}")
    String inputQueue;
    @Value("${spring.rabbitmq.port}")
    int port;


    @Bean
    Queue queue() {
        return new Queue(inputQueue, false);
    }

    @Bean
    ConnectionFactory connectionFactory() throws IOException {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(host);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(pwd);
        return connectionFactory;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, RabbitMQListener listener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(inputQueue);
        container.setMessageListener(listener);
        return container;
    }
}

package com.MK;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener implements MessageListener {
    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message.getBody());
        System.out.println("Received from rabbit :" + jsonNode.toPrettyString());
    }
}

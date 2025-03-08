package com.saas.discipline.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory, ObjectMapper mapper) {
        final var rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(jacksonConverter(mapper));
        return rabbitTemplate;
    }

    @Bean
    public Queue createDisciplineResourceQueue() {
        return new Queue("create-discipline-resource-queue", true); // Durable queue
    }
    @Bean
    public Queue deleteDisciplineResourceQueue() {
        return new Queue("delete-discipline-resource-queue", true); // Durable queue
    }
    @Bean
    public Queue disciplineResourceStatusQueue() {
        return new Queue("discipline-resource-status-queue", true); // Durable queue
    }
}

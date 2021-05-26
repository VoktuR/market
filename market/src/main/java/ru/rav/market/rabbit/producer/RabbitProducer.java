package ru.rav.market.rabbit.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitProducer {

    @Bean
    public Queue requestQueue() {
        return new Queue("requestOrderQueue", false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("producerTopicExchange");
    }

    @Bean
    public Binding producerBinding() {
        return BindingBuilder
                .bind(requestQueue())
                .to(topicExchange())
                .with("order");
    }

    @RabbitListener(queues = "responseOrderQueue")
    public void receive(String in) {
        log.info(in);
    }

}

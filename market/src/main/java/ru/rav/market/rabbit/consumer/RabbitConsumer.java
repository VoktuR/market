package ru.rav.market.rabbit.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rav.market.order.dto.OrderDto;


@Configuration
@RequiredArgsConstructor
public class RabbitConsumer {

    private final RabbitTemplate rabbitTemplate;

    @Bean
    public Queue responseQueue() {
        return new Queue("responseOrderQueue", false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("consumerFanoutExchange");
    }

    @Bean
    public Binding consumerBinding(FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(responseQueue())
                .to(fanoutExchange);
    }

    @RabbitListener(queues = "requestOrderQueue")
    public void process(OrderDto orderDto) {
        String sb = orderDto.getId() + " is processed";
        rabbitTemplate.convertAndSend("consumerFanoutExchange", "", sb);
    }

}

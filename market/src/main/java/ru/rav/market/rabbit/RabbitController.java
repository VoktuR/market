package ru.rav.market.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rav.market.order.dto.OrderDto;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RabbitController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/rabbit")
    public void sendOrder(@RequestBody OrderDto orderDto) {
        rabbitTemplate.convertAndSend("producerTopicExchange", "order", orderDto);
        log.info("Order was sent to third party service for processing");
    }


}

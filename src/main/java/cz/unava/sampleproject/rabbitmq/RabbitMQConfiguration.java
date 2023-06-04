package cz.unava.sampleproject.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange sampleProjectExchange() {
        return new DirectExchange(RabbitMQConstants.EXCHANGE_NAME);
    }

    @Bean
    public Queue queueSampleProject() {
        return new Queue(RabbitMQConstants.QUEUE_NAME, true);
    }

    @Bean
    public Binding sampleProjectBinding() {
        return BindingBuilder.bind(queueSampleProject()).to(sampleProjectExchange()).with(RabbitMQConstants.ROUTING_KEY);
    }

}

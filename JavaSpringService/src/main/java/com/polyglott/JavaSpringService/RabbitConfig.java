    package com.polyglott.JavaSpringService;

    import com.polyglott.JavaSpringService.djangoClient;
    import org.springframework.amqp.core.DirectExchange;


    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    @Configuration
    public class RabbitConfig {


        @Bean
        public DirectExchange itemExchange(){return new DirectExchange("item");}

        @Bean
        public djangoClient django(){
            return new djangoClient();
        }

    }

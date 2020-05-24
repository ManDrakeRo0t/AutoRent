package ru.bogatov.AutoRent.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bogatov.AutoRent.Services.*;

@Configuration
public class SpringConfig {

    @Bean
    public CarServiceable getCarService(){return new CarService();}

    @Bean
    public UserServiceable getUserService(){return new UserService();}

    @Bean
    public PunctsServiceable getPunctsService(){return new PunctsService();}

    @Bean
    public OrderServiceable getOrderService(){return new OrderService();}

}

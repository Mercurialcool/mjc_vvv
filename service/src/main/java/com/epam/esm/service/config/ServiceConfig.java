package com.epam.esm.service.config;

import com.epam.esm.dao.config.DaoConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = "com.epam.esm.service")
@Import({DaoConfig.class})
public class ServiceConfig {

    @Bean
    public ModelMapper dtoRouter() {
        return new ModelMapper();
    }
}

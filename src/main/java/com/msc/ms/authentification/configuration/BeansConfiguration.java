package com.msc.ms.authentification.configuration;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
@Slf4j
public class BeansConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        log.info("ModelMapper Bean");
        return new ModelMapper();
    }

    @Bean
    public SimpleDateFormat dateFormat() {
        log.info("SimpleDateFormat Bean");
        return new SimpleDateFormat("dd/MM/yyyy");
    }
}

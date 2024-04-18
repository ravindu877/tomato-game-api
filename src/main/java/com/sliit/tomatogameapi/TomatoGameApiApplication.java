package com.sliit.tomatogameapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.sliit.tomatogameapi")
public class TomatoGameApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomatoGameApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}

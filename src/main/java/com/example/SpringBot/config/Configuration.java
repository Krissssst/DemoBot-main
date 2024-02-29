package com.example.SpringBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@org.springframework.context.annotation.Configuration
@Data
@PropertySource("application.properties")
public class Configuration {

    @Value("${bot.name}")
    String bootName;

    @Value("${bot.token}")
    String bootToken;
}

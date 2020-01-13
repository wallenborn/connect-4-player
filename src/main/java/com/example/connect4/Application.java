/**************************************************************************
 * Project     : connect-4-player
 * File        : Application.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.example.connect4.game.Game;

/**
 * Application.
 *
 */
@SpringBootApplication
@ComponentScan("com.example")
public class Application  implements CommandLineRunner {

  private static final Logger logger     = LoggerFactory.getLogger(Application.class);
  
  @Autowired
  private Game player;
  
  
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("Starting Game");
    player.runGame();
  }
  

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

}

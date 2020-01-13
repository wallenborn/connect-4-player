/**************************************************************************
 * Project     : connect-4-player
 * File        : TestConfiguration.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TestConfiguration.
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.example")
public class TestApplicationConfiguration {

}

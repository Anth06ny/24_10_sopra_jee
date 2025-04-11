package org.example.messagerie2.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@Configuration
class AppConfig {

    @Bean
    @Qualifier("basic")
    open fun restTemplate() = RestTemplate()

    @Bean
    @LoadBalanced
    @Qualifier("loadbalancing")
    open fun restTemplateLB() = RestTemplate()
}
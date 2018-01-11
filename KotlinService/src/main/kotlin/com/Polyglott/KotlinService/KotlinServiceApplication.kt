package com.Polyglott.KotlinService

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

//import org.springframework.data.jpa.repository.config.EnableJpaRepositories


//
@Configuration
//@ComponentScan(basePackages = {"com.Polyglott.KotlinService.Repository",
 //   "com.Polyglott.KotlinService.Controller"} )
//@EntityScan("com.Polyglott.KotlinService.Model.Raid")
@EnableJpaRepositories("com.Polyglott.KotlinService.Repository")
@SpringBootApplication
class KotlinServiceApplication




fun main(args: Array<String>) {
    SpringApplication.run(KotlinServiceApplication::class.java, *args)
}

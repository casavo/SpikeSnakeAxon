package spikeSnakeAxon

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val appContext = SpringApplication.run(Application::class.java, *args)
}

// TODO: move this controller: https://stackoverflow.com/questions/31318107/spring-boot-cannot-access-rest-controller-on-localhost-404

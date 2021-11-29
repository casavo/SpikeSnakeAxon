package spikeSnakeAxon

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val appContext = SpringApplication.run(Application::class.java, *args)
}

var logger: Logger = LoggerFactory.getLogger(Application::class.java)
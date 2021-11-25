package spikeSnakeAxon

import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.JdbcTokenStore
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AxonConfig {
    // ...
    @Bean
    fun myTokenStore(): TokenStore {
        return JdbcTokenStore.builder()
            .build()
    }
}
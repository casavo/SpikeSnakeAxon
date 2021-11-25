package spikeSnakeAxon

import org.axonframework.common.jdbc.ConnectionProvider
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.eventhandling.tokenstore.jdbc.JdbcTokenStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration



@Configuration
 class AxonConfig {
    // ...
    @Autowired
    fun registerTokenStore(processingConfigurer: EventProcessingConfigurer)
    {
        val tokenStore: TokenStore = JdbcTokenStore.builder()
            .build();
    }
}
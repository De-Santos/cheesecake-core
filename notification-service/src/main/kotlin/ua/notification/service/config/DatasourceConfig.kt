package ua.notification.service.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
class DatasourceConfig(
    private val environment: Environment
) {
    @Bean
    fun dataSource(): DataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.driverClassName = environment.getProperty("spring.datasource.driver-class-name")
        hikariConfig.jdbcUrl = environment.getProperty("spring.datasource.url")
        hikariConfig.username = environment.getProperty("spring.datasource.username")
        hikariConfig.password = environment.getProperty("spring.datasource.password")
        return HikariDataSource(hikariConfig)
    }

    @Bean
    fun jdbcTemplate(dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}

package com.enlinka.peso_wise.config

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import javax.sql.DataSource

@Configuration
class FlywayConfig {

    @Autowired
    private lateinit var dataSource: DataSource

    @Bean(initMethod = "migrate")
    @DependsOn("dataSource")
    fun flyway(): Flyway {
        return Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:db/migration")
            .baselineOnMigrate(true)
            .validateOnMigrate(true)
            .outOfOrder(false)
            .load()
    }

    /**
     * Programmatic method to run migrations up
     * This can be called via custom endpoint or command line
     */
    fun migrateUp(): Int {
        return flyway().migrate().migrationsExecuted
    }

    /**
     * Programmatic method to undo last migration
     * This can be called via custom endpoint or command line
     */
    fun migrateDown(): Int {
        val flyway = flyway()
        val info = flyway.info()
        val current = info.current()
        
        if (current != null) {
            // Clean and then migrate to previous version
            flyway.clean()
            
            // Get all applied migrations except the last one
            val applied = info.applied()
            if (applied.size > 1) {
                val targetVersion = applied[applied.size - 2].version
                flyway.migrate()
                return 1
            } else {
                // If only one migration, clean everything
                return 1
            }
        }
        return 0
    }

    /**
     * Get migration status information
     */
    fun getMigrationInfo(): String {
        val info = flyway().info()
        val sb = StringBuilder()
        
        sb.appendLine("Migration Status:")
        sb.appendLine("Current Version: ${info.current()?.version ?: "None"}")
        sb.appendLine("Applied Migrations: ${info.applied().size}")
        sb.appendLine("Pending Migrations: ${info.pending().size}")
        
        info.all().forEach { migration ->
            sb.appendLine("${migration.version} - ${migration.description} [${migration.state}]")
        }
        
        return sb.toString()
    }
}

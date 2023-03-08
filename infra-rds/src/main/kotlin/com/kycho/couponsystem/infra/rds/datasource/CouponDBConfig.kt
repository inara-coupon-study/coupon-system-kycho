package com.kycho.couponsystem.infra.rds.datasource


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@ConditionalOnProperty(prefix = "spring.datasource.using", name = ["coupondb"])
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.kycho.couponsystem.domain.*.repository"],
    entityManagerFactoryRef = "couponDBEntityManager",
    transactionManagerRef = "couponDBTransactionManager"
)
class CouponDBConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.coupondb")
    fun couponDBDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean
    fun couponDBTransactionManager(jpaProperties: JpaProperties, hibernateProperties: HibernateProperties) =
        JpaTransactionManager(couponDBEntityManager(jpaProperties, hibernateProperties).`object`!!)

    @Bean
    fun couponDBEntityManager(
        jpaProperties: JpaProperties,
        hibernateProperties: HibernateProperties,
    ): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactoryBuilder = EntityManagerFactoryBuilder(
            HibernateJpaVendorAdapter(),
            hibernateProperties.determineHibernateProperties(jpaProperties.properties, HibernateSettings()),
            null
        )
        return entityManagerFactoryBuilder
            .dataSource(couponDBDataSource())
            .packages("com.kycho.couponsystem.domain")
            .build()
    }
}
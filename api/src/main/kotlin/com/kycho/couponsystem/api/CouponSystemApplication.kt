package com.kycho.couponsystem.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.kycho.couponsystem"])
@ConfigurationPropertiesScan
class CouponSystemApplication

fun main(args: Array<String>) {
	runApplication<CouponSystemApplication>(*args)
}

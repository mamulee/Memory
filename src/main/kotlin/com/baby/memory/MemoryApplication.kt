package com.baby.memory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MemoryApplication

fun main(args: Array<String>) {
    runApplication<MemoryApplication>(*args)
}

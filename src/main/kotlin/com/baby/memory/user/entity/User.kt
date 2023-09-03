package com.baby.memory.user.entity

import com.baby.memory.common.entity.BaseTimeEntity
import com.baby.memory.memory.entity.Memory
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
class User(
    val userEmail: String,
    var userPassword: String,
    var userName: String,
    @OneToMany(mappedBy = "user")
    val memories: MutableList<Memory> = mutableListOf(),
    @OneToMany(mappedBy = "user")
    val savedMemories: MutableList<Memory> = mutableListOf()
): BaseTimeEntity() {
    @Id @GeneratedValue
    @Column(name = "user_id")
    var id: Long = 0
        protected set

    fun updateUserPassowrd(passwordEncoder: PasswordEncoder, password: String) {
        this.userPassword = passwordEncoder.encode(password)
    }

    fun updateUserName(name: String) {
        this.userName = name
    }

    fun addSavedMemory(memory: Memory) {
        this.savedMemories.add(memory)
    }
    fun removeSavedMemory(memory: Memory) {
        this.savedMemories.remove(memory)
    }
}
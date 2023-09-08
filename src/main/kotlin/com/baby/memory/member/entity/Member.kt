package com.baby.memory.member.entity

import com.baby.memory.common.entity.BaseTimeEntity
import com.baby.memory.common.status.ROLE
import com.baby.memory.memory.entity.Memory
import jakarta.persistence.*

//import org.springframework.security.crypto.password.PasswordEncoder

@Entity
class Member(
    val memberEmail: String,
    var memberPassword: String,
    var memberName: String?,
    @OneToMany(mappedBy = "member")
    val memories: MutableList<Memory> = mutableListOf(),
    @OneToMany(mappedBy = "member")
    val savedMemories: MutableList<Memory> = mutableListOf(),
    @Enumerated(EnumType.STRING)
    var memberRole: ROLE? = null
): BaseTimeEntity() {
    @Id @GeneratedValue
    @Column(name = "member_id")
    var id: Long = 0
        protected set

//    fun updateUserPassowrd(passwordEncoder: PasswordEncoder, password: String) {
//        this.memberPassword = passwordEncoder.encode(password)
//    }

    fun updateUserName(name: String) {
        this.memberName = name
    }

    fun addSavedMemory(memory: Memory) {
        this.savedMemories.add(memory)
    }
    fun removeSavedMemory(memory: Memory) {
        this.savedMemories.remove(memory)
    }
}
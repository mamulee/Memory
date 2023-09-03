package com.baby.memory.memory.entity

import com.baby.memory.memory.entity.enum.ReactionStatus
import com.baby.memory.user.entity.User
import jakarta.persistence.*

@Entity
class Reaction(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    val memory: Memory,
    @Enumerated(EnumType.STRING)
    val status: ReactionStatus
) {
    @Id
    @GeneratedValue
    @Column(name = "reaction_id")
    var id: Long = 0
        protected set
}
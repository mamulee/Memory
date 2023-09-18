package com.baby.memory.memory.entity

import com.baby.memory.memory.entity.enum.ReactionStatus
import com.baby.memory.member.entity.Member
import jakarta.persistence.*

@Entity
class Reaction(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    val memory: Memory,
    @Enumerated(EnumType.STRING)
    var status: ReactionStatus
) {
    @Id
    @GeneratedValue
    @Column(name = "reaction_id")
    var id: Long = 0
        protected set
}
package com.baby.memory.memory.entity

import com.baby.memory.memory.entity.enum.ReactionStatus
import com.baby.memory.member.entity.Member
import jakarta.persistence.*

@Entity
class SavedMemory(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    val memory: Memory
) {
    @Id
    @GeneratedValue
    @Column(name = "savedMemory_id")
    var id: Long = 0
        protected set
}
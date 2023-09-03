package com.baby.memory.memory.entity

import com.baby.memory.common.entity.BaseTimeEntity
import com.baby.memory.user.entity.User
import jakarta.persistence.*

@Entity
class Comment(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id", nullable = false)
    var memory: Memory,
    var content: String
): BaseTimeEntity() {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    var id: Long = 0
        protected set

    fun updateContent(content: String) {
        this.content = content
    }
}
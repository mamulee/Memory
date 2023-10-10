package com.baby.memory.memory.entity

import com.baby.memory.common.entity.BaseTimeEntity
import com.baby.memory.member.entity.Member
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Comment(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id", nullable = false)
    var memory: Memory,
    var content: String,
    var isDeleted: Boolean = false
) : BaseTimeEntity() {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    var id: Long = 0
        protected set
}

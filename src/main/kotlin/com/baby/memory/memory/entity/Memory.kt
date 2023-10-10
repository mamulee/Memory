package com.baby.memory.memory.entity

import com.baby.memory.common.entity.BaseTimeEntity
import com.baby.memory.member.entity.Member
import com.baby.memory.memory.entity.enum.ReactionStatus
import jakarta.persistence.*

@Entity
class Memory(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,
    @Lob
    var content: String,
    @OneToMany(mappedBy = "memory")
    val comments: MutableList<Comment> = mutableListOf(),
    @OneToMany(mappedBy = "memory")
    val reactions: MutableList<Reaction> = mutableListOf(),
    var isDeleted: Boolean = false
) : BaseTimeEntity() {
    @Id
    @GeneratedValue
    @Column(name = "memory_id")
    var id: Long = 0
        protected set

    fun setWriter(member: Member) {
        this.member = member
        member.memories.add(this)
    }

    fun addComment(comment: Comment) {
        this.comments.add(comment)
        comment.memory = this
    }

    @get:Transient
    val likeCnt: Int
        get() = reactions.filter { it.status == ReactionStatus.LIKE }.size

    @get:Transient
    val sadCnt: Int
        get() = reactions.filter { it.status == ReactionStatus.SAD }.size

    @get:Transient
    val angryCnt: Int
        get() = reactions.filter { it.status == ReactionStatus.ANGRY }.size
}

package com.baby.memory.memory.entity

import com.baby.memory.common.entity.BaseTimeEntity
import com.baby.memory.memory.entity.enum.ReactionStatus
import com.baby.memory.user.entity.User
import jakarta.persistence.*

@Entity
class Memory(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,
    @Lob
    var content: String,
    @OneToMany(mappedBy = "memory")
    val comments: MutableList<Comment> = mutableListOf(),
    @OneToMany(mappedBy = "memory")
    val reactions: MutableList<Reaction> = mutableListOf()
): BaseTimeEntity() {
    @Id @GeneratedValue
    @Column(name = "memory_id")
    var id: Long = 0
        protected set

    fun setWriter(user: User) {
        this.user = user
        user.memories.add(this)
    }

    fun updateContent(content: String) {
        this.content = content
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
package com.baby.memory.member.entity

import com.baby.memory.common.entity.BaseTimeEntity
import com.baby.memory.common.status.ROLE
import com.baby.memory.memory.entity.Memory
import com.baby.memory.memory.entity.SavedMemory
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
class Member(
    val memberEmail: String,
    var memberPassword: String,
    var memberName: String? = null,
    // TODO : 프로필 사진 필드
    @ManyToMany
    @JoinTable(
        name = "member_relations",
        joinColumns = [JoinColumn(name = "followed_id")],
        inverseJoinColumns = [JoinColumn(name = "follower_id")]
    )
    val followers: MutableList<Member> = mutableListOf(),
    @ManyToMany(mappedBy = "followers")
    val followings: MutableList<Member> = mutableListOf(),
    @OneToMany(mappedBy = "member")
    val memories: MutableList<Memory> = mutableListOf(),
    @OneToMany(mappedBy = "member")
    val savedMemories: MutableList<SavedMemory> = mutableListOf(),
    @Enumerated(EnumType.STRING)
    var memberRole: ROLE? = null
) : BaseTimeEntity() {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long = 0
        protected set

    fun updateMemberPassowrd(passwordEncoder: PasswordEncoder, password: String) {
        this.memberPassword = passwordEncoder.encode(password)
    }

    fun updateUserName(name: String) {
        this.memberName = name
    }

    // TODO : 딱히 필요 없어진..?
    fun addFollowers(follower: Member) {
        followers.add(follower)
        follower.followings.add(this)
    }
    fun addFollowing(followed: Member) {
        followed.addFollowers(this)
    }
}

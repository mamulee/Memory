package com.baby.memory.member.dto.response

import com.baby.memory.member.entity.Member

data class MemberResponseDto(
    val id: Long,
    val memberEmail: String,
    val memberName: String,
    val followers: List<FollowRelatedDto>,
    val following: List<FollowRelatedDto>,
    val followersCnt: Int,
    val followingCnt: Int,
    val followingStatus: Boolean
) {
    companion object {
        fun of(member: Member, status: Boolean): MemberResponseDto = MemberResponseDto(
            id = member.id,
            memberEmail = member.memberEmail,
            memberName = member.memberName!!,
            followers = member.followers.map { FollowRelatedDto.of(it) },
            following = member.followings.map { FollowRelatedDto.of(it) },
            followersCnt = member.followers.size,
            followingCnt = member.followings.size,
            followingStatus = status
        )
    }
}

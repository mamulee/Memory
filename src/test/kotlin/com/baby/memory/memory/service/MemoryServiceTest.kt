//package com.baby.memory.memory.service
//
//import com.baby.memory.member.entity.Member
//import com.baby.memory.member.repository.MemberRepository
//import com.baby.memory.memory.dto.request.MemoryRequestDto
//import com.baby.memory.memory.repository.MemoryRepository
//import com.baby.memory.memory.service.impl.MemoryServiceImpl
//import io.kotest.core.spec.style.BehaviorSpec
//import io.kotest.core.spec.style.StringSpec
//import io.kotest.matchers.shouldBe
//import io.mockk.impl.annotations.MockK
//import io.mockk.mockk
//
//
//class MemoryServiceTest: BehaviorSpec({
//    @MockK
//    lateinit var memoryService: MemoryService
//
//    val memberRepository = mockk<MemberRepository>()
//    val memoryRepository = mockk<MemoryRepository>()
//    Given("테스트 할 상황") {
//        val service = MemoryServiceImpl(memberRepository, memoryRepository)
//        val member = Member("test@test.com", "aa", "name")
//        MemoryRequestDto(1L, "content", 0,0,0)
//        when("어떤 상황에서") {
//            service.createMemory(req)
//            then("결과는?") {
//
//            }
//        }
//    }
//})
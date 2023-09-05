//package com.baby.memory.memory.service
//
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
//   val memoryRepository = mockk<MemoryRepository>()
//    Given("테스트 할 상황") {
//        val service = MemoryServiceImpl(memoryRepository)
//        when("어떤 상황에서") {
//            then("결과는?") {
//
//            }
//        }
//    }
//})
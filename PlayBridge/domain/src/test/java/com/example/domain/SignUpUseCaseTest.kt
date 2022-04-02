package com.example.domain

import com.example.domain.model.SignUpModel
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.SignUpUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.*

class SignUpUseCaseTest {
    private val userRepository: UserRepository = mockk()

    @Test
    fun get() = runTest{
        //given
        val signUpModel = SignUpModel("test@test.com", "123345","TestUser","m",Date(123),
        true,false)
        val usecase = SignUpUseCase(userRepository)
        coEvery{ usecase(signUpModel) }
        //when
        //then
    }
}
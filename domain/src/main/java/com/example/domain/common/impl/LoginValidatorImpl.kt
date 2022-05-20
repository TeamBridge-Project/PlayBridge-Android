package com.example.domain.common.impl

import com.example.domain.common.LoginValidator
import javax.inject.Inject

internal class LoginValidatorImpl @Inject constructor() : LoginValidator {
    override fun isEmailValidity(email: String): Boolean {
        return email.matches("""^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[.][a-zA-Z]{2,3}$""".toRegex())
    }

    override fun isPasswordValidity(password: String): Boolean {
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$".toRegex())
    }
}